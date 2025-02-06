package br.com.postech.techchallenge.order_api.infrastructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "combos")
public class ComboEntity extends EntityBase {
    @ManyToOne
    private ProductEntity product;

    @ManyToMany
    private List<AddonEntity> addons = new ArrayList<>();
    private BigDecimal totalPrice = BigDecimal.ZERO;
}
