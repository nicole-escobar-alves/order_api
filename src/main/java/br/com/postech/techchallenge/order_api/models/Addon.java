package br.com.postech.techchallenge.order_api.models;

import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class Addon extends BaseDomain {
    private String name;
    private BigDecimal price = BigDecimal.ZERO;
    private ProductCategory productCategory;
    private Double discountPercent = 0.0;

    public void update(String name, BigDecimal price, Double discountPercent) {
        this.name = name;
        this.price = price;
        this.discountPercent = discountPercent;
    }

    public BigDecimal getPriceDiscounted() {
        return price.multiply(BigDecimal.valueOf(1 - discountPercent));
    }
}
