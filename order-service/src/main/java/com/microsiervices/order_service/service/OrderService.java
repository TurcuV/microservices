package com.microsiervices.order_service.service;

import com.microsiervices.events.InventoryFailedEvent;
import com.microsiervices.events.InventoryReservedEvent;
import com.microsiervices.order_service.dao.OrderDao;
import com.microsiervices.order_service.dto.OrderRequestDto;
import com.microsiervices.events.OrderCreatedEvent;
import com.microsiervices.order_service.model.Order;
import com.microsiervices.order_service.model.OrderItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderDao orderDao;
    private final WebClient.Builder webClientBuilder;
    private final KafkaProducerService kafkaProducerService;

    public String placeOrder(OrderRequestDto orderRequest) {
        Order order = new Order();
        order.setUserId(orderRequest.getUserId());
        order.setTotalAmount(orderRequest.getTotalAmount());

        List<OrderItem> orderItems = orderRequest.getOrderItems().stream()
                .map(item -> OrderItem.builder()
                        .productId(item.getProductId())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .build()
                ).toList();
        order.setOrderItems(orderItems);
        order.setOrderStatus("CREATED");
        orderDao.save(order);

        // Publish OrderCreatedEvent to Kafka

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        orderCreatedEvent.setOrderId(order.getId());
        orderCreatedEvent.setOrderItems(orderRequest.getOrderItems());
        kafkaProducerService.publishOrderCreatedEvent(orderCreatedEvent);

//        Order order = new Order();
//        order.setOrderNumber(UUID.randomUUID().toString());
//
//        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemsDtoList()
//                .stream()
//                .map(this::mapToDto)
//                .toList();
//        order.setOrderLineItems(orderLineItems);
//
//        List<String> skuCodes = order.getOrderLineItems().stream()
//            .map(OrderLineItem::getSkuCode)
//            .toList();
//
//        // Call Inventory Service, and place order if product is in stock
//
//        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
//            .uri("http://inventory-service/api/inventory",
//                 uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
//            .retrieve()
//            .bodyToMono(InventoryResponse[].class)
//            .block();
//
//
//        boolean allProductsInStock = Arrays.stream(inventoryResponses)
//            .allMatch(InventoryResponse::isInStock);
//
//        if(allProductsInStock) {
//            orderDao.save(order);
//            return "Order place successfully!!!";
//        } else {
//            return "No products in stock!!!";
//        }

        return "";
    }

    @KafkaListener(topics = "inventory-events", groupId = "order-group")
    public void handleInventoryEvents(Object event) {
        if (((ConsumerRecord<?, ?>) event).value() instanceof InventoryReservedEvent reservedEvent) {
            Order order = orderDao.findById(reservedEvent.getOrderId()).orElseThrow();
            order.setOrderStatus("CONFIRMED");
            orderDao.save(order);
        } else if (((ConsumerRecord<?, ?>) event).value() instanceof InventoryFailedEvent reservedEvent) {
            Order order = orderDao.findById(reservedEvent.getOrderId()).orElseThrow();
            order.setOrderStatus("CANCELLED");
            orderDao.save(order);
        }
    }

//
//    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemsDto) {
//        OrderLineItem orderLineItems = new OrderLineItem();
//        orderLineItems.setPrice(orderLineItemsDto.getPrice());
//        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
//        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
//        return orderLineItems;
//    }

    public List<Order> getOrders() {
        return orderDao.findAll();
    }
}
