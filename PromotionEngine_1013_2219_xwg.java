// 代码生成时间: 2025-10-13 22:19:40
package com.example;

import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.Introspected;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * Promotion Engine that determines the applicable promotions for a given product.
 */
@Requires(env = "production")
public class PromotionEngine {

    private final List<Promotion> promotions; // List of promotions available

    // Constructor injection for promotions
    public PromotionEngine(@NotNull List<Promotion> promotions) {
        this.promotions = promotions;
    }

    /**
     * Applies all applicable promotions to the given product.
     * @param product to which promotions are to be applied
     * @return a list of applied promotions
     */
    public List<Promotion> applyPromotions(Product product) {
        List<Promotion> appliedPromotions = promotions.stream()
                .filter(promotion -> promotion.isApplicable(product))
                .toList();
        return appliedPromotions;
    }
}

/**
 * Represents a Promotion.
 */
@Introspected
public interface Promotion {
    
    /**
     * Determines if the promotion is applicable to a given product.
     * @param product the product to check eligibility
     * @return true if promotion is applicable, false otherwise
     */
    boolean isApplicable(Product product);
}

/**
 * Represents a Product.
 */
@Introspected
public class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters and setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
