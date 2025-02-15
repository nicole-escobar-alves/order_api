<<<<<<<< HEAD:src/test/java/br/com/postech/techchallenge/order_api/unitTests/controller/AddonControllerTest.java
package br.com.postech.techchallenge.order_api.unitTests.controller;

import br.com.postech.techchallenge.order_api.controller.AddonController;
import br.com.postech.techchallenge.order_api.dto.addon.AddonDto;
import br.com.postech.techchallenge.order_api.dto.addon.CreateAddonDto;
import br.com.postech.techchallenge.order_api.dto.addon.UpdateAddonDto;
import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import br.com.postech.techchallenge.order_api.exception.EntityNotFoundException;
import br.com.postech.techchallenge.order_api.service.internalService.AddonService;
========
package br.com.postech.techchallenge.orderapi.controller;

import br.com.postech.techchallenge.orderapi.dto.addon.AddonDto;
import br.com.postech.techchallenge.orderapi.dto.addon.CreateAddonDto;
import br.com.postech.techchallenge.orderapi.dto.addon.UpdateAddonDto;
import br.com.postech.techchallenge.orderapi.enums.ProductCategory;
import br.com.postech.techchallenge.orderapi.exception.EntityNotFoundException;
import br.com.postech.techchallenge.orderapi.service.internal.AddonService;
>>>>>>>> 2b22bf7f4b2f320fe86d03630d6207be83bfa663:src/test/java/br/com/postech/techchallenge/orderapi/controller/AddonControllerTest.java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddonControllerTest {

    @InjectMocks
    private AddonController controller;
    @Mock
    private AddonService addonService;


    @Test
    void create() {
        CreateAddonDto createAddonDto = new CreateAddonDto("Bacon", BigDecimal.TEN, ProductCategory.LANCHE, 0.0);

        var result = controller.create(createAddonDto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void listAllByProductCategory() {
        AddonDto addonDto = new AddonDto(1L, "Bacon", BigDecimal.TEN, 0.0, "Lanche");
        List<AddonDto> dto = List.of(addonDto);
        when(addonService.findAllByProductCategory("Lanche")).thenReturn(dto);

        var result = controller.listAllByProductCategory("Lanche");

        assertAll(
                ()-> assertEquals(dto, result.getBody()),
                ()-> assertEquals(HttpStatus.OK, result.getStatusCode())
        );
    }

    @Test
    void update() throws EntityNotFoundException {
        UpdateAddonDto updateAddonDto = new UpdateAddonDto();

        var result = controller.update(1L, updateAddonDto);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void delete() throws EntityNotFoundException {
        var result = controller.delete(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}