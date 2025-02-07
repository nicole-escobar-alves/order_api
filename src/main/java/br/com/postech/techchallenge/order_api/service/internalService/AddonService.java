package br.com.postech.techchallenge.order_api.service.internalService;

import br.com.postech.techchallenge.order_api.dto.addon.CreateAddonDto;
import br.com.postech.techchallenge.order_api.infrastructure.entities.AddonEntity;
import br.com.postech.techchallenge.order_api.infrastructure.repositories.IAddonJpaRepository;
import br.com.postech.techchallenge.order_api.mapper.IAddonMapper;
import br.com.postech.techchallenge.order_api.models.Addon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddonService {

    @Autowired
    private IAddonJpaRepository addonRepository;
    @Autowired
    private IAddonMapper mapper;

    public void Create(CreateAddonDto createAddon) {

        Addon addonResponse = mapper.toAddon(createAddon);

        AddonEntity entity = mapper.toEntity(addonResponse);

        var domainSaved = addonRepository.save(entity);
    }
}
