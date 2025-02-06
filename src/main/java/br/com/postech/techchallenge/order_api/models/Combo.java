package br.com.postech.techchallenge.order_api.models;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Combo extends BaseDomain {

    private Product product;
    private List<Addon> addons = new ArrayList<>();
    private BigDecimal totalPrice = BigDecimal.ZERO;



}
