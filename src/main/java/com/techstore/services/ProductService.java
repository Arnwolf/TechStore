package com.techstore.services;

import com.techstore.dto.ProductDTO;
import com.techstore.entities.Photo;
import com.techstore.entities.Product;
import com.techstore.entities.ProductParameter;
import com.techstore.entities.Review;
import com.techstore.repositories.*;

import java.util.*;
import java.util.stream.Collectors;


public class ProductService {
    private ProductRepository productRepository = new ProductRepository();
    private ReviewRepository reviewRepository = new ReviewRepository();
    private ParametersRepository parametersRepository = new ParametersRepository();
    private PhotoRepository photoRepository = new PhotoRepository();

    private static ProductService instance = new ProductService();
    public static ProductService getInstance() { return instance; }


    private ProductService() { }


    public List<Product> popularProducts() {
        return productRepository.findPopular();
    }


    public Product product(final Integer id) { return productRepository.findById(id); }

    public List<Product> products(final Collection<Integer> ids) {
        return ids.isEmpty() ? new ArrayList<>() : productRepository.findByIds(ids);
    }

    private ProductDTO createDTO(final Product product,
                                 final List<Review> productReviews,
                                 final List<ProductParameter> productParameters,
                                 final List<Photo> productPhotos) {
        List<ProductParameter> changeableParameters = new ArrayList<>();
        String productDescription = null;
        ProductParameter descriptionParameter = null;

        for (final ProductParameter parameter : productParameters) {
            if (parameter.getCategoryParameter().getChangeable())
                changeableParameters.add(parameter);
            else if (parameter.getCategoryParameter().getName().equalsIgnoreCase("description"))
                descriptionParameter = parameter;
        }

        if (descriptionParameter != null)
            productDescription = descriptionParameter.getValue();

        productParameters.remove(descriptionParameter);
        productParameters.removeAll(changeableParameters);

        List<String> photos = productPhotos
                .stream()
                .map(Photo::getPhoto)
                .collect(Collectors.toList());

        photos.add(product.getMainPhoto());

        int totalScore = 0;

        for (Review review : productReviews)
            totalScore += review.getRating();

        if (!productReviews.isEmpty())
            totalScore = totalScore / productReviews.size();


        ProductDTO dto = new ProductDTO();
        dto.setDescription(productDescription);
        dto.setTotalReviewScore(totalScore);
        dto.setChangeableParameters(changeableParameters);
        dto.setParameters(productParameters);
        dto.setProduct(product);
        dto.setReviews(productReviews);
        dto.setProductPhotos(photos);

        return dto;
    }

    private ProductDTO detailedProduct(final Integer categoryParamId, final String productParamValue) {
        Product product =
                productRepository.findByCategoryParameterIdAndParameterValue(categoryParamId, productParamValue).get(0);
        List<ProductParameter> parameters = parametersRepository.findByProductIdAndName(product.getId(), product.getName());
        List<Photo> photos = photoRepository.findByProductId(product.getId());
        List<Review> reviews = reviewRepository.findByProductId(product.getId());

        return createDTO(product, reviews, parameters, photos);
    }

    private ProductDTO detailedProduct(final Integer id) {
        Product product = productRepository.findById(id);
        List<ProductParameter> parameters = parametersRepository.findByProductIdAndName(id, product.getName());
        List<Photo> photos = photoRepository.findByProductId(id);
        List<Review> reviews = reviewRepository.findByProductId(id);

        return createDTO(product, reviews, parameters, photos);
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

    public void addReview(final Review review) { reviewRepository.add(review); }
}
