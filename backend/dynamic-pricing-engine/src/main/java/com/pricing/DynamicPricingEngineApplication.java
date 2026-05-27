package com.pricing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DynamicPricingEngineApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamicPricingEngineApplication.class, args);
    }
}