package br.com.postech.techchallenge.order_api.infrastructure.repositories;

import br.com.postech.techchallenge.order_api.models.BaseDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJpaRepositoryBase<T extends BaseDomain> extends JpaRepository<T, Long> {

}
