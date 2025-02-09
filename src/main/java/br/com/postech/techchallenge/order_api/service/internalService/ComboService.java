package br.com.postech.techchallenge.order_api.service.internalService;

import br.com.postech.techchallenge.order_api.dto.combo.CreateComboDto;
import br.com.postech.techchallenge.order_api.mapper.IComboMapper;
import br.com.postech.techchallenge.order_api.models.Combo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComboService {

    @Autowired
    IComboMapper comboMapper;
    @Autowired
    ProductService productService;
    @Autowired
    AddonService addonService;

    public Combo Create(CreateComboDto comboDto) throws EntityNotFoundException {

        var product = productService.FindById(comboDto.getProductId());

        Combo combo = new Combo(product);

        for (Long id : comboDto.getAddonsId()) {
            combo.AddAddon(addonService.FindById(id));
        }

        return combo;
    }
}
