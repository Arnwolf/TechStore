package com.techstore.services;

import com.techstore.entities.ProductDetail;
import com.techstore.repositories.ProductDetailsRepository;


public class ProductDetailsService {
    private ProductDetailsRepository productDetailsRepository = new ProductDetailsRepository();

    private static ProductDetailsService productDetailsService = new ProductDetailsService();
    public static ProductDetailsService getInstance() { return productDetailsService; }

    private ProductDetailsService() { }


    public void updateViewedTimes(final Integer itemId) {
        ProductDetail productDetail = productDetailsRepository.findByProductId(itemId);

        int viewedTimes = productDetail.getViewedTimes();
        productDetail.setViewedTimes(++viewedTimes);

        productDetailsRepository.update(productDetail);
    }

    public void updateSoldTimes(final Integer itemId, final Integer quantity) {
        ProductDetail productDetail = productDetailsRepository.findByProductId(itemId);

        int soldTimes = productDetail.getSoldTimes() + quantity;
        productDetail.setSoldTimes(soldTimes);

        productDetailsRepository.update(productDetail);
    }
}
