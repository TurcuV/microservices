package com.microsiervices.inventory_service.dao;

import com.microsiervices.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InventoryDao extends JpaRepository<Inventory, Long> {
    List<Inventory> findByProductId(UUID productId);
}
