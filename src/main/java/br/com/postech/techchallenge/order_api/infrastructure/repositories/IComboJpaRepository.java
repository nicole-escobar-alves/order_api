package br.com.postech.techchallenge.order_api.infrastructure.repositories;

import br.com.postech.techchallenge.order_api.models.Combo;
import org.springframework.stereotype.Repository;

@Repository
public interface IComboJpaRepository extends IJpaRepositoryBase<Combo> {
}
