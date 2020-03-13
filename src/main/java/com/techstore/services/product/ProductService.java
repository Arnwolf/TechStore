package com.techstore.services.product;

import com.techstore.dto.PreviewProductDto;
import com.techstore.dto.DetailedProductDto;
import com.techstore.entities.Product;
import java.util.Collection;
import java.util.List;


public interface ProductService {
    enum ProductsCriteria {
        POPULAR,
        HIGHLY_RATED,
        DISCOUNT,
        NEW
    }

    List<PreviewProductDto> categoryProducts(final int categoryId);
    List<PreviewProductDto> similarParameterProducts(final int categoryParameterId, final String parameterValue);
    List<PreviewProductDto> searchProducts(final String search);
    List<PreviewProductDto> productByCriteria(final ProductsCriteria criteria);
    List<PreviewProductDto> findByIds(final Collection<Integer> productIds);

    DetailedProductDto findById(final int productId);
    Product findProduct(final int productId);
}
