package br.com.postech.techchallenge.order_api.mapper;

import br.com.postech.techchallenge.order_api.dto.product.CreateProductDto;
import br.com.postech.techchallenge.order_api.dto.product.ProductDto;
import br.com.postech.techchallenge.order_api.infrastructure.entities.ProductEntity;
import br.com.postech.techchallenge.order_api.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = IProductCategoryMapper.class)
public interface IProductMapper extends IBaseDomainMapper<Product, ProductEntity> {



}
