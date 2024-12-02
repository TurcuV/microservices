package com.microsiervices.order_service.service;

import com.microsiervices.order_service.dao.OrderDao;
import com.microsiervices.order_service.dto.OrderLineItemDto;
import com.microsiervices.order_service.dto.OrderRequestDto;
import com.microsiervices.order_service.model.Order;
import com.microsiervices.order_service.model.OrderLineItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderDao orderDao;

    public void placeOrder(OrderRequestDto orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItems(orderLineItems);
        orderDao.save(order);

        log.info("Order Placed: {}", order.getOrderNumber());
    }

    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemsDto) {
        OrderLineItem orderLineItems = new OrderLineItem();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }

    public List<Order> getOrders() {
        return orderDao.findAll();
    }
}
