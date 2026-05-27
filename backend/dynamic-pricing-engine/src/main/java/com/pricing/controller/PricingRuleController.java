package com.pricing.controller;

import com.pricing.model.PricingRule;
import com.pricing.service.PricingRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rules")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PricingRuleController {

    private final PricingRuleService ruleService;

    @GetMapping
    public List<PricingRule> getAllRules() {
        return ruleService.getAllRules();
    }

    @PostMapping
    public PricingRule createRule(@RequestBody PricingRule rule) {
        return ruleService.createRule(rule);
    }

    @PutMapping("/{id}/toggle")
    public PricingRule toggleRule(@PathVariable Long id) {
        return ruleService.toggleRule(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRule(@PathVariable Long id) {
        ruleService.deleteRule(id);
        return ResponseEntity.ok("Rule deleted");
    }
}