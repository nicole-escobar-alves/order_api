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

    public void AddCombo(Combo combo) {
        combos.add(combo);
        increasePrice(combo.getTotalPrice());
    }

    private void increasePrice(BigDecimal price) {
        this.totalPrice = totalPrice.add(price);
    }

    public void UpdateOrderStatus(OrderStatus status) {
        this.orderStatus = status;
        if (OrderStatus.FINISHED == status)
            updateFinishedTime();
    }

    private void updateFinishedTime() {
        this.finishedTime = LocalDateTime.now();
    }
}
