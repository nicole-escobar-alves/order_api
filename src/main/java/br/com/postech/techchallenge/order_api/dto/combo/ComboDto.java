package br.com.postech.techchallenge.order_api.dto.combo;

import br.com.postech.techchallenge.order_api.dto.addon.AddonDto;
import br.com.postech.techchallenge.order_api.dto.product.ProductDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ComboDto {
    private ProductDto product;
    private List<AddonDto> addons;
    private BigDecimal totalPrice;
}
