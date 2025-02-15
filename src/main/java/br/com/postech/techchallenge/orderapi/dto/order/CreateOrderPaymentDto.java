package br.com.postech.techchallenge.orderapi.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@ToString
public class CreateOrderPaymentDto {
    private String orderId;
    private BigDecimal amount;
}
