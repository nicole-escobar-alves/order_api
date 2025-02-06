package br.com.postech.techchallenge.order_api.infrastructure.repositories;

import br.com.postech.techchallenge.order_api.infrastructure.entities.CustomerEntity;

import java.util.Optional;

public interface ICustomerJpaRepository extends IJpaRepositoryBase<CustomerEntity> {
    Optional<CustomerEntity> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
