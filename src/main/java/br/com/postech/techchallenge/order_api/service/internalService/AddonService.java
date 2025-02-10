package br.com.postech.techchallenge.order_api.service.internalService;

import br.com.postech.techchallenge.order_api.dto.addon.AddonDto;
import br.com.postech.techchallenge.order_api.dto.addon.CreateAddonDto;
import br.com.postech.techchallenge.order_api.dto.addon.UpdateAddonDto;
import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import br.com.postech.techchallenge.order_api.infrastructure.repositories.IAddonJpaRepository;
import br.com.postech.techchallenge.order_api.mapper.IAddonMapper;
import br.com.postech.techchallenge.order_api.models.Addon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import br.com.postech.techchallenge.order_api.exception.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AddonService {

    private final IAddonJpaRepository addonRepository;
    private final IAddonMapper mapper;

    public void create(CreateAddonDto createAddon) {

        Addon addon = mapper.toAddon(createAddon);

        addonRepository.save(addon);
    }

    public List<AddonDto> findAllByProductCategory(String productCategoryName) {

        var addonsList = addonRepository.findAllByIsActiveTrueAndProductCategory(ProductCategory.fromDisplayName(productCategoryName));

        return mapper.toAddonListDto(addonsList);
    }

    public Addon findById(Long id) throws EntityNotFoundException {

        return addonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The addon was not found."));
    }

    public void update(Long id, UpdateAddonDto dto) throws EntityNotFoundException {

        Addon addon = addonRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The addon was not found."));

        addon.update(dto.getName(), dto.getPrice(), dto.getDiscountPercent());

        addonRepository.save(addon);
    }

    public void delete(Long id) throws EntityNotFoundException {

        if (!addonRepository.existsById(id))
            throw new EntityNotFoundException("The addon was not found.");

        addonRepository.deleteById(id);
    }
}
