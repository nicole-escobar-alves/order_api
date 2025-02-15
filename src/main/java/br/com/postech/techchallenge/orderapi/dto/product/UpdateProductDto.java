package br.com.postech.techchallenge.orderapi.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UpdateProductDto {
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Double discountPercent;

}
