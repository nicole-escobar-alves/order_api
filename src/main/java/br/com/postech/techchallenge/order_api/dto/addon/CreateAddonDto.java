package br.com.postech.techchallenge.order_api.dto.addon;

import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class CreateAddonDto {

    public String name;

    public BigDecimal price;

    public ProductCategory productCategory;

    public Double discountPercent;
}
