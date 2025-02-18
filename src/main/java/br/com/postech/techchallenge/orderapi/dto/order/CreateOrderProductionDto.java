package br.com.postech.techchallenge.orderapi.dto.order;

import br.com.postech.techchallenge.orderapi.dto.combo.ComboProductionDto;
import br.com.postech.techchallenge.orderapi.models.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class CreateOrderProductionDto {
    private Long id;
    private List<ComboProductionDto> combos;

    public CreateOrderProductionDto(Order orderSaved) {
        id = orderSaved.getId();
        combos = orderSaved.getCombos().stream().map(ComboProductionDto::new).toList();
    }
}
