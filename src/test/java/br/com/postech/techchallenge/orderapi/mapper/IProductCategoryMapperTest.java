<<<<<<<< HEAD:src/test/java/br/com/postech/techchallenge/order_api/unitTests/mapper/IProductCategoryMapperTest.java
package br.com.postech.techchallenge.order_api.unitTests.mapper;

import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import br.com.postech.techchallenge.order_api.mapper.IProductCategoryMapper;
========
package br.com.postech.techchallenge.orderapi.mapper;

import br.com.postech.techchallenge.orderapi.enums.ProductCategory;
>>>>>>>> 2b22bf7f4b2f320fe86d03630d6207be83bfa663:src/test/java/br/com/postech/techchallenge/orderapi/mapper/IProductCategoryMapperTest.java
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class IProductCategoryMapperTest {

    private IProductCategoryMapper mapper = Mappers.getMapper(IProductCategoryMapper.class);

    @Test
    void mapToString() {
        String result = mapper.map(ProductCategory.LANCHE);
        assertEquals("LANCHE", result);
    }

    @Test
    void mapToProductCategory() {
        var result = mapper.map("LANCHE");
        assertEquals(ProductCategory.LANCHE, result);
    }
}