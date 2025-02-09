package br.com.postech.techchallenge.order_api.models;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Combo extends BaseDomain {

    private Product product;
    private List<Addon> addons = new ArrayList<>();
    private BigDecimal totalPrice = BigDecimal.ZERO;

    public Combo(Product product) {

        if (product == null) throw new IllegalArgumentException("A Product is required.");

        this.product = product;

        increasePrice(product.getPriceDiscounted());
    }

    public void AddAddon(Addon addon) {

        boolean categoryEquals = product.getProductCategory().equals(addon.getProductCategory());

        if (!categoryEquals)
            throw new IllegalArgumentException(addon.getName() + " não pode ser ao produto " + product.getName());

        addons.add(addon);

        increasePrice(addon.getPriceDiscounted());
    }

    private void increasePrice(BigDecimal price) {
        this.totalPrice = totalPrice.add(price);
    }

}
