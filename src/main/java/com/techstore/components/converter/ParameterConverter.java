package com.techstore.components.converter;

import com.techstore.dto.ParameterDto;
import com.techstore.entities.ProductParameter;

public class ParameterConverter implements Converter<ParameterDto, ProductParameter> {

    @Override
    public ParameterDto convert(final ProductParameter toConvert) {
        ParameterDto dto = new ParameterDto();
        dto.categoryParameterId = toConvert.getCategoryParameter().getId();
        dto.parameterSymbol = toConvert.getCategoryParameter().getSymbol();
        dto.isSearchable = toConvert.getCategoryParameter().getSearchable();
        dto.productValue = toConvert.getValue();
        dto.id = toConvert.getId();
        dto.productId = toConvert.getItem().getId();
        dto.parameterName = toConvert.getCategoryParameter().getName();

        return dto;
    }
}
