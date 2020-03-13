package com.techstore.services.product;

import com.techstore.components.converter.Converter;
import com.techstore.components.converter.DetailedProductConverter;
import com.techstore.components.converter.ParameterConverter;
import com.techstore.components.converter.PreviewProductConverter;
import com.techstore.dto.ParameterDto;
import com.techstore.dto.PreviewProductDto;
import com.techstore.dto.DetailedProductDto;
import com.techstore.entities.Product;
import com.techstore.entities.ProductParameter;
import com.techstore.entities.Review;

import java.util.*;
import java.util.stream.Collectors;


public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository = new ProductRepository();
    private final ParametersRepository parametersRepository = new ParametersRepository();

    private final Converter<PreviewProductDto, Product> previewProductConverter = new PreviewProductConverter();
    private final Converter<DetailedProductDto, Product> productConverter = new DetailedProductConverter();
    private final Converter<ParameterDto, ProductParameter> parametersConverter = new ParameterConverter();

    private static final ProductServiceImpl instance = new ProductServiceImpl();
    public static ProductServiceImpl getInstance() { return instance; }

    private ProductServiceImpl() { }


    @Override
    public List<PreviewProductDto> categoryProducts(final int categoryId) {
        return productRepository.findAllByCategory(categoryId).stream()
                .map(previewProductConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<PreviewProductDto> similarParameterProducts(final int categoryParameterId, final String parameterValue)
    {
        return productRepository.findByCategoryParameterIdAndParameterValue(categoryParameterId, parameterValue)
                .stream()
                .map(previewProductConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<PreviewProductDto> searchProducts(final String search) {
        return productRepository.findByName(search).stream()
                .map(previewProductConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<PreviewProductDto> productByCriteria(final ProductsCriteria criteria) {
        switch (criteria) {
            case POPULAR:
                return productRepository.findPopular(true, true, 3, true)
                        .stream()
                        .map(previewProductConverter::convert)
                        .collect(Collectors.toList());

            case DISCOUNT:
                return productRepository.findByDiscount()
                        .stream()
                        .map(previewProductConverter::convert)
                        .collect(Collectors.toList());

            case NEW:
                return productRepository.findByNew()
                        .stream()
                        .map(previewProductConverter::convert)
                        .collect(Collectors.toList());

            case HIGHLY_RATED:
                return productRepository.findByRating(3)
                        .stream()
                        .map(previewProductConverter::convert)
                        .collect(Collectors.toList());
            default:
                return null;
        }
    }

    @Override
    public DetailedProductDto findById(final int productId) {
        Product product = productRepository.findDetailedById(productId);
        ProductDetailsService productDetailsService = ProductDetailsService.getInstance();

        productDetailsService.updateViewedTimes(productId);

        return setAdditionalProductInfo(product, productConverter.convert(product));
    }

    @Override
    public Product findProduct(final int productId) { return productRepository.findById(productId); }

    @Override
    public List<PreviewProductDto> findByIds(final Collection<Integer> productIds) {
        if (productIds.isEmpty())
            throw new IllegalArgumentException("Empty ids!");

        return productRepository.findByIds(productIds).stream()
                .map(previewProductConverter::convert)
                .collect(Collectors.toList());
    }

    private DetailedProductDto setAdditionalProductInfo(final Product product, final DetailedProductDto dto) {
        dto.changeableParameters = parametersRepository.findChangeableByName(dto.product.name)
                .stream()
                .map(parametersConverter::convert)
                .collect(Collectors.toList());

        int totalScore = 0;

        if (!product.getReviews().isEmpty()) {
            for (final Review review : product.getReviews())
                totalScore += review.getRating();

            totalScore /= product.getReviews().size();
        }

        dto.totalReviewsScore = totalScore;

        return dto;
    }
}
