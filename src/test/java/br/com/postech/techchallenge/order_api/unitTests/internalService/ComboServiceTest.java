package br.com.postech.techchallenge.order_api.unitTests.internalService;

import br.com.postech.techchallenge.order_api.dto.combo.CreateComboDto;
import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import br.com.postech.techchallenge.order_api.exception.EntityNotFoundException;
import br.com.postech.techchallenge.order_api.models.Addon;
import br.com.postech.techchallenge.order_api.models.Product;
import br.com.postech.techchallenge.order_api.service.internalService.AddonService;
import br.com.postech.techchallenge.order_api.service.internalService.ComboService;
import br.com.postech.techchallenge.order_api.service.internalService.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComboServiceTest {

    @InjectMocks
    private ComboService comboService;
    @Mock
    private ProductService productService;
    @Mock
    private AddonService addonService;

    @Test
    void create() throws EntityNotFoundException {
        CreateComboDto dto = new CreateComboDto(1L, List.of(1L));
        Product product = new Product();
        product.setProductCategory(ProductCategory.LANCHE);
        Addon addon = new Addon();
        addon.setProductCategory(ProductCategory.LANCHE);
        when(productService.findById(1L)).thenReturn(product);
        when(addonService.findById(1L)).thenReturn(addon);

        var result = comboService.create(dto);

        assertEquals(product, result.getProduct());
    }
}