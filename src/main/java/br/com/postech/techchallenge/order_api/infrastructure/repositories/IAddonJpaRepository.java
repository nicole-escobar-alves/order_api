package br.com.postech.techchallenge.order_api.infrastructure.repositories;

import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import br.com.postech.techchallenge.order_api.infrastructure.entities.AddonEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAddonJpaRepository extends IJpaRepositoryBase<AddonEntity> {
    List<AddonEntity> findAllByIsActiveTrueAndProductCategory(ProductCategory productCategory);

    @Override
    @Modifying
    @Query("UPDATE AddonEntity a SET a.isActive = false WHERE a.id = :id")
    void deleteById(@NonNull @Param("id") Long id);

    List<AddonEntity> findAllByIsActiveFalse();
}
