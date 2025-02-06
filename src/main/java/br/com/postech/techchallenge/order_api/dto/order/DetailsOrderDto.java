package br.com.postech.techchallenge.order_api.dto.order;

import br.com.postech.techchallenge.order_api.dto.combo.ComboDto;
import br.com.postech.techchallenge.order_api.dto.customer.CustomerDto;
import br.com.postech.techchallenge.order_api.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class DetailsOrderDto {
    private Long id;
    private List<ComboDto> combos;
    private BigDecimal totalPrice;
    private OrderStatus orderStatus;
    private CustomerDto customer;
    private String elapsedTime;
    private String estimatedTime;
}
