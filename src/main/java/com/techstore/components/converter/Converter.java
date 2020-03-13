package com.techstore.components.converter;

public interface Converter<DtoType, EntityType> {

    DtoType convert(final EntityType toConvert);
}
