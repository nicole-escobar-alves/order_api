package br.com.postech.techchallenge.order_api.infrastructure.repositories;

import br.com.postech.techchallenge.order_api.infrastructure.entities.ComboEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface IComboJpaRepository extends IJpaRepositoryBase<ComboEntity> {
}
