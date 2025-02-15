<<<<<<<< HEAD:src/test/java/br/com/postech/techchallenge/order_api/unitTests/models/ComboTest.java
package br.com.postech.techchallenge.order_api.unitTests.models;

import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import br.com.postech.techchallenge.order_api.models.Addon;
import br.com.postech.techchallenge.order_api.models.Combo;
import br.com.postech.techchallenge.order_api.models.Product;
========
package br.com.postech.techchallenge.orderapi.models;

import br.com.postech.techchallenge.orderapi.enums.ProductCategory;
>>>>>>>> 2b22bf7f4b2f320fe86d03630d6207be83bfa663:src/test/java/br/com/postech/techchallenge/orderapi/models/ComboTest.java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ComboTest {

    @Test
    void addComboShouldThrowExceptionWhenCategoryIsDifference(){
        Product product = new Product();
        product.setProductCategory(ProductCategory.LANCHE);
        Addon addon = new Addon();
        addon.setProductCategory(ProductCategory.ACOMPANHAMENTO);
        Combo combo = new Combo(product);

        assertThrows(IllegalArgumentException.class, ()-> combo.addAddon(addon));
        assertFalse(combo.getAddons().contains(addon));
    }

}