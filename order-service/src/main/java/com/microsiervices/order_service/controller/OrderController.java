package com.microsiervices.order_service.controller;

import com.microsiervices.order_service.dto.OrderRequestDto;
import com.microsiervices.order_service.model.Order;
import com.microsiervices.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody OrderRequestDto orderRequest) {
        orderService.placeOrder(orderRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrders() {
       return orderService.getOrders();
    }

}
