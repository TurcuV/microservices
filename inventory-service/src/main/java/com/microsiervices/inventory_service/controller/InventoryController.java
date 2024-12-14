package com.microsiervices.inventory_service.controller;

import com.microsiervices.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
//        log.info("Received inventory check request for skuCode: {}", skuCode);
//        return inventoryService.isInStock(skuCode);
//    }

}
