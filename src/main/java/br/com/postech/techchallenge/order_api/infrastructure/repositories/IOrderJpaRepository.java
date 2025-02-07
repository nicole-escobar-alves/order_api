package br.com.postech.techchallenge.order_api.infrastructure.repositories;

import br.com.postech.techchallenge.order_api.enums.OrderStatus;
import br.com.postech.techchallenge.order_api.infrastructure.entities.OrderEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderJpaRepository extends IJpaRepositoryBase<OrderEntity> {

    List<OrderEntity> findAllByOrderStatus(OrderStatus orderStatus);

    List<OrderEntity> findAllByCustomerCpf(String cpf);
}
