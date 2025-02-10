package br.com.postech.techchallenge.order_api.infrastructure.repositories;

import br.com.postech.techchallenge.order_api.enums.OrderStatus;
import br.com.postech.techchallenge.order_api.models.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderJpaRepository extends IJpaRepositoryBase<Order> {

    List<Order> findAllByOrderStatus(OrderStatus orderStatus);

    List<Order> findAllByCustomerCpf(String cpf);
}
