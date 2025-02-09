package br.com.postech.techchallenge.order_api.mapper;

import br.com.postech.techchallenge.order_api.dto.combo.ComboDto;
import br.com.postech.techchallenge.order_api.dto.combo.CreateComboDto;
import br.com.postech.techchallenge.order_api.infrastructure.entities.ComboEntity;
import br.com.postech.techchallenge.order_api.models.Combo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IComboMapper extends IBaseDomainMapper<Combo, ComboEntity> {


    ComboDto toComboDto(CreateComboDto createCombo);
}
