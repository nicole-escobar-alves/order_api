package br.com.postech.techchallenge.order_api.mapper;

import br.com.postech.techchallenge.order_api.dto.addon.AddonDto;
import br.com.postech.techchallenge.order_api.dto.addon.CreateAddonDto;
import br.com.postech.techchallenge.order_api.models.Addon;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = IProductCategoryMapper.class)
public interface IAddonMapper{

    AddonDto toAddonDto(Addon addon);

    Addon toAddon(CreateAddonDto dto);

    List<AddonDto> toAddonListDto(List<Addon> domains);
}
