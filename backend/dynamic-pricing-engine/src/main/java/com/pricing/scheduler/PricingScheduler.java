package com.pricing.scheduler;

import com.pricing.service.PricingEngineService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PricingScheduler {

    private final PricingEngineService pricingEngineService;

    // runs every 5 minutes
    @Scheduled(fixedRate = 300000)
    public void schedulePriceRecalculation() {
        System.out.println("Scheduler running — recalculating prices...");
        pricingEngineService.recalculateAllPrices();
    }
}