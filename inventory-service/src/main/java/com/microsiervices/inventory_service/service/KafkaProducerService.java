package com.microsiervices.inventory_service.service;

import com.microsiervices.events.InventoryFailedEvent;
import com.microsiervices.events.InventoryReservedEvent;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishInventoryReservedEvent(InventoryReservedEvent event) {
        Mono.fromRunnable(() -> kafkaTemplate.send("inventory-events", event)).subscribe();
    }

    public void publishInventoryFailedEvent(InventoryFailedEvent event) {
        Mono.fromRunnable(() -> kafkaTemplate.send("inventory-events", event)).subscribe();
    }
}