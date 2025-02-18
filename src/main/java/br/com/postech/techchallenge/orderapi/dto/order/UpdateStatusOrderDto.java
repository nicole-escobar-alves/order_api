package br.com.postech.techchallenge.orderapi.dto.order;

import br.com.postech.techchallenge.orderapi.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusOrderDto {
    OrderStatus orderStatus;
}
