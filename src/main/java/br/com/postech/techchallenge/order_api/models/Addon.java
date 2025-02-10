package br.com.postech.techchallenge.order_api.models;

import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "addons")
public class Addon extends BaseDomain {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price = BigDecimal.ZERO;

    private Double discountPercent = 0.0;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    public void update(String name, BigDecimal price, Double discountPercent) {
        this.name = name;
        this.price = price;
        this.discountPercent = discountPercent;
    }

    public BigDecimal getPriceDiscounted() {
        return price.multiply(BigDecimal.valueOf(1 - discountPercent));
    }
}
