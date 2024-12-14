package com.microsiervices.inventory_service.config;

//@Configuration
//@EnableKafka
public class KafkaConfig {

//    @Bean
//    public ConsumerFactory<String, OrderCreatedEvent> consumerFactory() {
//        JsonDeserializer<OrderCreatedEvent> deserializer = new JsonDeserializer<>(OrderCreatedEvent.class);
//        deserializer.addTrustedPackages("*"); // Allow all packages; better to specify your package
//
//        Map<String, Object> config = new HashMap<>();
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
//
//        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, OrderCreatedEvent> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, OrderCreatedEvent> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
}