package br.com.postech.techchallenge.orderapi.dto.addon;

import br.com.postech.techchallenge.orderapi.models.Addon;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AddonProductionDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Double discount_percent;
    private String product_category;

    public AddonProductionDto(Addon addon){
        id = addon.getId();
        name = addon.getName();
        price = addon.getPrice();
        discount_percent = addon.getDiscountPercent();
        product_category = addon.getProductCategory().getDisplayName();
    }
}
