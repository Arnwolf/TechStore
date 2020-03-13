package com.techstore.components.converter;

import com.techstore.dto.WishDto;
import com.techstore.entities.Wish;


public class WishConverter implements Converter<WishDto, Wish> {
    private PreviewProductConverter productConverter = new PreviewProductConverter();

    @Override
    public WishDto convert(final Wish toConvert) {
        WishDto dto = new WishDto();
        dto.id = toConvert.getId();
        dto.userName = toConvert.getUser().getName();
        dto.product = productConverter.convert(toConvert.getProduct());

        return dto;
    }
}
