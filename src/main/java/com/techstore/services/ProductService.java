package com.techstore.services;

import com.techstore.components.ProductConverter;
import com.techstore.dto.ProductDTO;
import com.techstore.entities.Photo;
import com.techstore.entities.Product;
import com.techstore.entities.ProductParameter;
import com.techstore.entities.Review;
import com.techstore.repositories.*;
import java.util.*;


public class ProductService {
    private ProductRepository productRepository = new ProductRepository();
    private ParametersRepository parametersRepository = new ParametersRepository();
    private PhotoRepository photoRepository = new PhotoRepository();

    private static ProductService instance = new ProductService();
    public static ProductService getInstance() { return instance; }


    private ProductService() { }


    public List<Product> popularProducts() { return productRepository.findPopular(); }

    public Product product(final Integer id) { return productRepository.findById(id); }

    public List<Product> products(final Collection<Integer> ids) {
        return ids.isEmpty() ? new ArrayList<>() : productRepository.findByIds(ids);
    }

    private ProductDTO detailedProduct(final Integer categoryParamId, final String productParamValue) {
        Product product =
                productRepository.findByCategoryParameterIdAndParameterValue(categoryParamId, productParamValue).get(0);
        List<ProductParameter> parameters = parametersRepository.findByProductIdAndName(product.getId(), product.getName());
        List<Photo> photos = photoRepository.findByProductId(product.getId());
        List<Review> reviews = ReviewsService.getInstance().reviewList(product);

        ProductDetailsService.getInstance().updateViewedTimes(product.getId());

        return ProductConverter.convert(product, reviews, parameters, photos);
    }

    private ProductDTO detailedProduct(final Integer id) {
        Product product = productRepository.findById(id);
        List<ProductParameter> parameters = parametersRepository.findByProductIdAndName(id, product.getName());
        List<Photo> photos = photoRepository.findByProductId(id);
        List<Review> reviews = ReviewsService.getInstance().reviewList(product);

        ProductDetailsService.getInstance().updateViewedTimes(product.getId());

        return ProductConverter.convert(product, reviews, parameters, photos);
    }

    public List<Product> searchedProducts(final Map<String, String> paramMap) {

        if (paramMap.get("categoryID") != null)
            return productRepository.findAllByCategory(Integer.parseInt(paramMap.get("categoryID")));
        else if (paramMap.get("categoryParamId") != null && paramMap.get("itemParamValue") != null)
            return productRepository.findByCategoryParameterIdAndParameterValue(
                    Integer.parseInt(paramMap.get("categoryParamId")),
                    paramMap.get("itemParamValue")
            );
        else if (paramMap.get("search") != null)
            return productRepository.findByName(paramMap.get("search"));
        else
            return new ArrayList<>();
    }

    public ProductDTO detailedSearchedProduct(final Map<String, String> paramMap) {

        if (paramMap.get("ItemID") != null)
            return detailedProduct(Integer.parseInt(paramMap.get("ItemID")));
        else if (paramMap.get("categoryParamId") != null && paramMap.get("itemParamValue") != null)
            return detailedProduct(
                    Integer.parseInt(paramMap.get("categoryParamId")),
                    paramMap.get("itemParamValue")
            );
        else
            return null;
    }
}
