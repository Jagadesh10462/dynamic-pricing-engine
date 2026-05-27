package com.pricing.repository;

import com.pricing.model.PricingRule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PricingRuleRepository extends JpaRepository<PricingRule, Long> {
    List<PricingRule> findByActive(Boolean active);
}