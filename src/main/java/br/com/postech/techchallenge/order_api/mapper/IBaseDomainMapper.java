package br.com.postech.techchallenge.order_api.mapper;

import br.com.postech.techchallenge.order_api.infrastructure.entities.EntityBase;
import br.com.postech.techchallenge.order_api.models.BaseDomain;

import java.util.List;

public interface IBaseDomainMapper<T extends BaseDomain, U extends EntityBase> {
     T toDomain(U entityBase);
     U toEntity(T domain);
     List<T> toDomains(List<U> entities);
}
