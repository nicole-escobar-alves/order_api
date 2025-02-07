package br.com.postech.techchallenge.order_api.infrastructure.repositories;

import br.com.postech.techchallenge.order_api.infrastructure.entities.CustomerEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerJpaRepository extends IJpaRepositoryBase<CustomerEntity> {
    Optional<CustomerEntity> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
