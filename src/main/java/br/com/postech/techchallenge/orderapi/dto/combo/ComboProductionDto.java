package br.com.postech.techchallenge.orderapi.dto.combo;

import br.com.postech.techchallenge.orderapi.dto.addon.AddonDto;
import br.com.postech.techchallenge.orderapi.dto.addon.AddonProductionDto;
import br.com.postech.techchallenge.orderapi.dto.product.ProductDto;
import br.com.postech.techchallenge.orderapi.dto.product.ProductProductionDto;
import br.com.postech.techchallenge.orderapi.models.Combo;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ComboProductionDto {
    private Long id;
    private ProductProductionDto product;
    private List<AddonProductionDto> addons;

    public ComboProductionDto(Combo combo) {
        id = combo.getId();
        product = new ProductProductionDto(combo.getProduct());
        addons = combo.getAddons().stream().map(AddonProductionDto::new).toList();
    }
}
