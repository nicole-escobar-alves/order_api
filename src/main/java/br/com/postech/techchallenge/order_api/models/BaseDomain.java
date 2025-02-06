package br.com.postech.techchallenge.order_api.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public abstract class BaseDomain {
    protected Long id;
    protected LocalDateTime creationTime;
    protected Boolean isActive;
}
