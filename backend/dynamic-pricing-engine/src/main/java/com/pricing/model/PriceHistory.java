package com.pricing.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "price_history")
@Data
public class PriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private String productName;

    private Double oldPrice;
    private Double newPrice;

    private String reason;
    private LocalDateTime changedAt;

    @PrePersist
    public void onCreate() {
        changedAt = LocalDateTime.now();
    }
}
