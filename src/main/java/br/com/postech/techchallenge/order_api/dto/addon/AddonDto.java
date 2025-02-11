package br.com.postech.techchallenge.order_api.dto.addon;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AddonDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Double discountPercent;
    private String productCategoryName;
}
