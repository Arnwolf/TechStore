package com.techstore.components.converter;

import com.techstore.dto.PreviewProductDto;
import com.techstore.entities.Product;

public class PreviewProductConverter implements Converter<PreviewProductDto, Product> {
    @Override
    public PreviewProductDto convert(final Product toConvert) {
        PreviewProductDto previewProduct = new PreviewProductDto();
        previewProduct.id = toConvert.getId();
        previewProduct.availability = toConvert.getAvailability();
        previewProduct.categoryName = toConvert.getCategory().getName();
        previewProduct.discount = toConvert.getDiscount();
        previewProduct.isNew = toConvert.isNew();
        previewProduct.manufacturer = toConvert.getManufacturer();
        previewProduct.name = toConvert.getName();
        previewProduct.photo = toConvert.getMainPhoto();
        previewProduct.price = toConvert.getPrice();

        return previewProduct;
    }
}
