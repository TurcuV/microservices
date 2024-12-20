package com.microsiervices.order_service.service;

import com.microsiervices.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishOrderCreatedEvent(OrderCreatedEvent event) {
        Mono.fromRunnable(() -> kafkaTemplate.send("order-created-topic", event)).subscribe();
    }
}
