package br.com.postech.techchallenge.order_api.mapper;

import br.com.postech.techchallenge.order_api.dto.product.CreateProductDto;
import br.com.postech.techchallenge.order_api.dto.product.ProductDto;
import br.com.postech.techchallenge.order_api.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = IProductCategoryMapper.class)
public interface IProductMapper {


    ProductDto toProductDto(Product product);


    @Mapping(target = "estimatedTime", ignore = true)
    Product toProduct(CreateProductDto dto);

    List<ProductDto> toProductListDto(List<Product> products);


}
