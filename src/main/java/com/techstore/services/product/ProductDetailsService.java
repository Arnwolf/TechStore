package com.techstore.services.product;

import com.techstore.entities.ProductDetail;
import javax.persistence.EntityNotFoundException;


public class ProductDetailsService {
    private final ProductDetailsRepository productDetailsRepository = new ProductDetailsRepository();

    private static final ProductDetailsService productDetailsService = new ProductDetailsService();
    public static ProductDetailsService getInstance() { return productDetailsService; }

    private ProductDetailsService() { }


    private ProductDetail findDetail(final int productId) {
        return productDetailsRepository.findByProductId(productId)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(productId)));
    }

    void updateViewedTimes(final int productId) {
        ProductDetail productDetail = findDetail(productId);

        int viewedTimes = productDetail.getViewedTimes();
        productDetail.setViewedTimes(++viewedTimes);

        productDetailsRepository.update(productDetail);
    }

    public void updateSoldTimes(final int productId, final Integer quantity) {
        ProductDetail productDetail = findDetail(productId);

        int soldTimes = productDetail.getSoldTimes() + quantity;
        productDetail.setSoldTimes(soldTimes);

        productDetailsRepository.update(productDetail);
    }
}
