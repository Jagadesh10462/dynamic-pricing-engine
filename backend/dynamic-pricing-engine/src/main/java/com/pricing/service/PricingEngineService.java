package com.pricing.service;

import com.pricing.model.*;
import com.pricing.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PricingEngineService {

    private final ProductRepository productRepository;
    private final PricingRuleRepository ruleRepository;
    private final PriceHistoryRepository historyRepository;

    public void recalculateAllPrices() {
        List<Product> products = productRepository.findAll();
        List<PricingRule> activeRules = ruleRepository.findByActive(true);

        for (Product product : products) {
            double newPrice = product.getBasePrice();
            String reason = "No rule applied";

            for (PricingRule rule : activeRules) {
                if (rule.getRuleType().equals("STOCK_BASED")) {
                    // low stock → markup price
                    if (product.getStockQuantity() < rule.getStockThreshold()
                            && rule.getMarkupPercent() != null) {
                        newPrice = newPrice * (1 + rule.getMarkupPercent() / 100);
                        reason = "Rule: " + rule.getRuleName() + " | Low stock markup applied";
                    }
                    // high stock → discount price
                    if (product.getStockQuantity() > rule.getStockThreshold() * 3
                            && rule.getDiscountPercent() != null) {
                        newPrice = newPrice * (1 - rule.getDiscountPercent() / 100);
                        reason = "Rule: " + rule.getRuleName() + " | High stock discount applied";
                    }
                }
            }

            // save history only if price actually changed
            if (product.getCurrentPrice() == null || 
                    Double.compare(newPrice, product.getCurrentPrice()) != 0) {
                PriceHistory history = new PriceHistory();
                history.setProductId(product.getId());
                history.setProductName(product.getName());
                history.setOldPrice(product.getCurrentPrice());
                history.setNewPrice(newPrice);
                history.setReason(reason);
                historyRepository.save(history);

                product.setCurrentPrice(newPrice);
                productRepository.save(product);
            }
        }
    }
}