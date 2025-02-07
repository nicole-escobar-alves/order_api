package br.com.postech.techchallenge.order_api.models;


import br.com.postech.techchallenge.order_api.enums.OrderStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseDomain {
    private List<Combo> combos = new ArrayList<>();
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private OrderStatus orderStatus = OrderStatus.CREATED;
    private Customer customer;
    private LocalDateTime finishedTime;

}
