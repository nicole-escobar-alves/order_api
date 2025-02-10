package br.com.postech.techchallenge.order_api.infrastructure.repositories;

import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import br.com.postech.techchallenge.order_api.models.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductJpaRepository extends IJpaRepositoryBase<Product> {

    List<Product> findAllByIsActiveTrueAndProductCategory(ProductCategory productCategory);

    @Override
    @Modifying
    @Query("UPDATE Product p SET p.isActive = false WHERE p.id = :id")
    void deleteById(@NonNull @Param("id") Long id);


}
