package br.com.postech.techchallenge.order_api.models;

import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
@Data
@EqualsAndHashCode(callSuper = true)
public class Addon extends BaseDomain {
    public String name;
    public BigDecimal price = BigDecimal.ZERO;
    public Double discountPercent = 0.0;
    public ProductCategory productCategory;

    public void update(String name, BigDecimal price, Double discountPercent) {
        this.name = name;
        this.price = price;
        this.discountPercent = discountPercent;
    }

    public BigDecimal getPriceDiscounted() {
        return price.multiply(BigDecimal.valueOf(1 - discountPercent));
    }
}
