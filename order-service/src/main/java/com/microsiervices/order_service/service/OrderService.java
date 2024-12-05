package com.microsiervices.order_service.service;

import com.microsiervices.order_service.dao.OrderDao;
import com.microsiervices.order_service.dto.InventoryResponse;
import com.microsiervices.order_service.dto.OrderLineItemDto;
import com.microsiervices.order_service.dto.OrderRequestDto;
import com.microsiervices.order_service.model.Order;
import com.microsiervices.order_service.model.OrderLineItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderDao orderDao;
    private final WebClient.Builder webClientBuilder;

    public String placeOrder(OrderRequestDto orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItems(orderLineItems);

        List<String> skuCodes = order.getOrderLineItems().stream()
            .map(OrderLineItem::getSkuCode)
            .toList();

        // Call Inventory Service, and place order if product is in stock

        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
            .uri("http://inventory-service/api/inventory",
                 uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
            .retrieve()
            .bodyToMono(InventoryResponse[].class)
            .block();


        boolean allProductsInStock = Arrays.stream(inventoryResponses)
            .allMatch(InventoryResponse::isInStock);

        if(allProductsInStock) {
            orderDao.save(order);
            return "Order place successfully!!!";
        } else {
            return "No products in stock!!!";
        }
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
