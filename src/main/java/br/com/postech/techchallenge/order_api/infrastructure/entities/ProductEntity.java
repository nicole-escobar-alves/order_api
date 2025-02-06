package br.com.postech.techchallenge.order_api.infrastructure.entities;

import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Duration;

@Getter
@Setter
@Entity
@Table(name = "producties")
public class ProductEntity extends EntityBase {

    @Column(nullable = false)
    private String name;
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Duration estimatedTime;

    private Double discountPercent;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
}