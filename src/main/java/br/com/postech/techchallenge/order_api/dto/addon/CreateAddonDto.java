package br.com.postech.techchallenge.order_api.dto.addon;

import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import lombok.*;

import java.math.BigDecimal;


@Getter
@AllArgsConstructor
public class CreateAddonDto {

    private String name;

    private BigDecimal price;

    private ProductCategory productCategory;

    private Double discountPercent;
}
