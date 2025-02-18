package br.com.postech.techchallenge.orderapi.dto.product;

import br.com.postech.techchallenge.orderapi.models.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Duration;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductProductionDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double discount_percent;
    private String product_category;
    private Long estimated_time;

    public ProductProductionDto(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        discount_percent = product.getDiscountPercent();
        product_category = product.getProductCategory().getDisplayName();
        estimated_time = product.getEstimatedTime().toMinutes();
    }
}
