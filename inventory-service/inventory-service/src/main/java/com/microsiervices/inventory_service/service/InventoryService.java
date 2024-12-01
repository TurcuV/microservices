package com.microsiervices.inventory_service.service;

import com.microsiervices.inventory_service.dao.InventoryDao;
import com.microsiervices.inventory_service.dto.InventoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryDao inventoryDao;

    public List<InventoryResponse> isInStock(String skuCode) {
        log.info("Checking Inventory");
        return inventoryDao.findBySkuCodeIn(List.of(skuCode)).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }
}
