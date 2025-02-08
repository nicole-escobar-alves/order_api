package br.com.postech.techchallenge.order_api.models;

import br.com.postech.techchallenge.order_api.dto.product.UpdateProductDto;
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
    private ProductCategory productCategory;
    private Duration estimatedTime;

    public void update(UpdateProductDto productDto) {
        this.name = productDto.getName();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
        this.discountPercent = productDto.getDiscountPercent();
    }

    public BigDecimal getPriceDiscounted() {
        return price.multiply(BigDecimal.valueOf(1 - discountPercent));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Duration getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Duration estimatedTime) {
        this.estimatedTime = estimatedTime;
    }
}
