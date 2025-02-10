package br.com.postech.techchallenge.order_api.mapper;

import br.com.postech.techchallenge.order_api.dto.combo.ComboDto;
import br.com.postech.techchallenge.order_api.dto.combo.CreateComboDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IComboMapper {


    ComboDto toComboDto(CreateComboDto createCombo);
}
