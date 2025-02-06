package br.com.postech.techchallenge.order_api.infrastructure.repositories;

import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import br.com.postech.techchallenge.order_api.infrastructure.entities.ProductEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;

public interface IProductJpaRepository extends IJpaRepositoryBase<ProductEntity> {

    //Alterar para @SqlRestriction() na entity para não trazer os ativos
    List<ProductEntity> findAllByIsActiveTrueAndProductCategory(ProductCategory productCategory);

    @Override
    @Modifying
    @Query("UPDATE ProductEntity p SET p.isActive = false WHERE p.id = :id")
    void deleteById(@NonNull @Param("id") Long id);
}
