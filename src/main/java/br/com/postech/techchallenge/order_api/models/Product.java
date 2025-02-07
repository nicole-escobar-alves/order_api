package br.com.postech.techchallenge.order_api.models;

import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.Duration;

@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseDomain {

    private String name;
    private String description;
    private BigDecimal price = BigDecimal.ZERO;
    private Double discountPercent = 0.0;
    private Duration estimatedTime;
    private ProductCategory productCategory;


}
