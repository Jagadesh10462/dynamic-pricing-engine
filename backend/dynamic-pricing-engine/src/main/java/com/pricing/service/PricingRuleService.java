package com.pricing.service;

import com.pricing.model.PricingRule;
import com.pricing.repository.PricingRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PricingRuleService {

    private final PricingRuleRepository ruleRepository;

    public List<PricingRule> getAllRules() {
        return ruleRepository.findAll();
    }

    public PricingRule createRule(PricingRule rule) {
        return ruleRepository.save(rule);
    }

    public PricingRule toggleRule(Long id) {
        PricingRule rule = ruleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
        rule.setActive(!rule.getActive());
        return ruleRepository.save(rule);
    }

    public void deleteRule(Long id) {
        ruleRepository.deleteById(id);
    }
}