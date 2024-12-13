package com.microsiervices.order_service.controller;

import com.microsiervices.order_service.dto.OrderRequestDto;
import com.microsiervices.order_service.model.Order;
import com.microsiervices.order_service.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequestDto orderRequest) {
        orderService.placeOrder(orderRequest);
        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));
    }

    @GetMapping("/test")
    public void test() {
        orderService.testKafka();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrders() {
       return orderService.getOrders();
    }

    public CompletableFuture<String> fallbackMethod(OrderRequestDto orderRequest, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(() -> "Something went wrong in order-service!!!");
    }

}
