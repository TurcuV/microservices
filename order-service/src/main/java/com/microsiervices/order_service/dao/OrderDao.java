package com.microsiervices.order_service.dao;

import com.microsiervices.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, Long> {
}
