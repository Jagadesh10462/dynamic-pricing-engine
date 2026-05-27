package com.pricing.controller;

import com.pricing.model.PriceHistory;
import com.pricing.model.Product;
import com.pricing.repository.PriceHistoryRepository;
import com.pricing.service.PricingEngineService;
import com.pricing.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final PriceHistoryRepository historyRepository;
    private final PricingEngineService pricingEngineService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product updated) {
        return productService.updateProduct(id, updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted");
    }

    @GetMapping("/{id}/history")
    public List<PriceHistory> getPriceHistory(@PathVariable("id") Long id) {
        return historyRepository.findByProductIdOrderByChangedAtDesc(id);
    }

    @PostMapping("/recalculate")
    public ResponseEntity<?> triggerRecalculation() {
        pricingEngineService.recalculateAllPrices();
        return ResponseEntity.ok("Prices recalculated successfully");
    }
}