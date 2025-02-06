package br.com.postech.techchallenge.order_api.infrastructure.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class CustomerEntity extends EntityBase {

    private String name;
    private String cpf;
    private String email;
}
