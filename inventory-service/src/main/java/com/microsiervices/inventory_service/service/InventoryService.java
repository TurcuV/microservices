package com.microsiervices.inventory_service.service;

import com.microsiervices.inventory_service.dao.InventoryDao;
import com.microsiervices.inventory_service.dao.OrderItemDto;
import com.microsiervices.events.InventoryFailedEvent;
import com.microsiervices.events.InventoryReservedEvent;
import com.microsiervices.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryDao inventoryDao;
    private final KafkaProducerService kafkaProducerService;

    public boolean isInStock(OrderCreatedEvent event) {
        log.info("Checking Inventory");

        OrderItemDto order = event.getOrderItems().get(0); // get only first item for tests
        return inventoryDao.findByProductId(order.getProductId()).stream()
                .anyMatch(product -> product.getQuantity() >= order.getQuantity());
    }

    @KafkaListener(topics = "order-created-topic",groupId = "inventory-group")
    public void listenOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("Received Order: {}", event);

        if (isInStock(event)) {
            kafkaProducerService.publishInventoryReservedEvent(new InventoryReservedEvent(event.getOrderId()));
        } else {
            kafkaProducerService.publishInventoryFailedEvent(new InventoryFailedEvent(event.getOrderId(), "Stock not available"));
        }
    }
}
