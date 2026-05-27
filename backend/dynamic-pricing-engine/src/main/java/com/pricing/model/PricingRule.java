package com.pricing.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pricing_rules")
@Data
public class PricingRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;

    // STOCK_BASED or DEMAND_BASED
    private String ruleType;

    // if stock < stockThreshold → apply discount
    private Integer stockThreshold;

    // discount percentage e.g. 10 means 10%
    private Double discountPercent;

    // markup percentage e.g. 15 means 15% increase
    private Double markupPercent;

    private Boolean active;
}