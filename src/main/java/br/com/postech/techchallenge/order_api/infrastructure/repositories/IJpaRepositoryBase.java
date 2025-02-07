package br.com.postech.techchallenge.order_api.infrastructure.repositories;

import br.com.postech.techchallenge.order_api.infrastructure.entities.EntityBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJpaRepositoryBase<T extends EntityBase> extends JpaRepository<T, Long> {

}
