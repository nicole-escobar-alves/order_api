package br.com.postech.techchallenge.order_api.service.internalService;

import br.com.postech.techchallenge.order_api.dto.addon.AddonDto;
import br.com.postech.techchallenge.order_api.dto.addon.CreateAddonDto;
import br.com.postech.techchallenge.order_api.dto.addon.UpdateAddonDto;
import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import br.com.postech.techchallenge.order_api.infrastructure.entities.AddonEntity;
import br.com.postech.techchallenge.order_api.infrastructure.repositories.IAddonJpaRepository;
import br.com.postech.techchallenge.order_api.mapper.IAddonMapper;
import br.com.postech.techchallenge.order_api.models.Addon;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddonService {

    @Autowired
    private IAddonJpaRepository addonRepository;
    @Autowired
    private IAddonMapper mapper;

    public void Create(CreateAddonDto createAddon) {

        Addon addonResponse = mapper.toAddon(createAddon);

        AddonEntity entity = mapper.toEntity(addonResponse);

        addonRepository.save(entity);
    }

    public List<AddonDto> FindAllByProductCategory(String productCategoryName) {

        var addonList = addonRepository.findAllByIsActiveTrueAndProductCategory(ProductCategory.fromDisplayName(productCategoryName));

        var addonsList = mapper.toDomains(addonList);

        return mapper.toAddonListDto(addonsList);
    }

    public void Update(Long id, UpdateAddonDto dto) {

        AddonEntity addonEntity = addonRepository.findById(id).get();

        Addon addon = mapper.toDomain(addonEntity);
        addon.update(dto.getName(), dto.getPrice(), dto.getDiscountPercent());

        addonEntity = mapper.toEntity(addon);

        addonRepository.save(addonEntity);
    }

    public void Delete(Long id) throws EntityNotFoundException {

        if (!addonRepository.existsById(id))
            throw new EntityNotFoundException("The addon was not found.");

        addonRepository.deleteById(id);
    }
}
