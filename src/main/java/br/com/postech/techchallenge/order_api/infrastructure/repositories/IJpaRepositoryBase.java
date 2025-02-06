package br.com.postech.techchallenge.order_api.infrastructure.repositories;

import br.com.postech.techchallenge.order_api.infrastructure.entities.EntityBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJpaRepositoryBase<T extends EntityBase> extends JpaRepository<T, Long> {

}
