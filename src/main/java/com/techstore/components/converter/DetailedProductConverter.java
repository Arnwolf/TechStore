package com.techstore.components.converter;

import com.techstore.dto.DetailedProductDto;
import com.techstore.dto.ParameterDto;
import com.techstore.dto.ReviewDto;
import com.techstore.entities.Photo;
import com.techstore.entities.Product;
import com.techstore.entities.ProductParameter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class DetailedProductConverter implements Converter<DetailedProductDto, Product> {
    private ParameterConverter parameterConverter = new ParameterConverter();
    private PreviewProductConverter previewProductConverter = new PreviewProductConverter();
    private ReviewConverter reviewConverter = new ReviewConverter();

    @Override
    public DetailedProductDto convert(final Product toConvert) {
        List<ParameterDto> parameters = new ArrayList<>();
        String productDescription = "";

        for (final ProductParameter parameter : toConvert.getParameters()) {
            if (parameter.getCategoryParameter().getName().equalsIgnoreCase("description")) {
                productDescription = parameter.getValue();
                continue;
            }

            ParameterDto parameterDto = parameterConverter.convert(parameter);

            if (!parameter.getCategoryParameter().getChangeable())
                parameters.add(parameterDto);
        }

        List<String> photos = toConvert.getPhotos()
                .stream()
                .map(Photo::getPhoto)
                .collect(Collectors.toList());

        List<ReviewDto> reviews =toConvert.getReviews()
                .stream()
                .map(reviewConverter::convert)
                .collect(Collectors.toList());


        photos.add(toConvert.getMainPhoto());


        DetailedProductDto dto = new DetailedProductDto();
        dto.description = productDescription;
        dto.parameters = parameters;
        dto.product = previewProductConverter.convert(toConvert);
        dto.productPhotos = photos;
        dto.reviews = reviews;

        return dto;
    }
}
