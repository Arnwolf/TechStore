package com.techstore.components;

import com.techstore.dto.ProductDTO;
import com.techstore.entities.Photo;
import com.techstore.entities.Product;
import com.techstore.entities.ProductParameter;
import com.techstore.entities.Review;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


public class ProductConverter {
    public static ProductDTO convert(final Product product,
                                     final List<Review> productReviews,
                                     final List<ProductParameter> productParameters,
                                     final List<Photo> productPhotos) {
        List<ProductParameter> changeableParameters = new ArrayList<>();
        Iterator<ProductParameter> iterator = productParameters.iterator();

        ProductDTO dto = new ProductDTO();
        dto.setProduct(product);
        dto.setReviews(productReviews);

        while (iterator.hasNext()) {
            ProductParameter parameter = iterator.next();

            if (parameter.getCategoryParameter().getChangeable())
                changeableParameters.add(iterator.next());
            else if (parameter.getCategoryParameter().getName().equalsIgnoreCase("description")) {
                dto.setDescription(parameter.getValue());
                iterator.remove();
            }
        }

        productParameters.removeAll(changeableParameters);

        dto.setChangeableParameters(changeableParameters);
        dto.setParameters(productParameters);

        List<String> photos = productPhotos
                .stream()
                .map(Photo::getPhoto)
                .collect(Collectors.toList());

        photos.add(product.getMainPhoto());

        dto.setProductPhotos(photos);

        int totalScore = 0;

        for (Review review : productReviews)
            totalScore += review.getRating();

        if (!productReviews.isEmpty())
            totalScore /= productReviews.size();

        dto.setTotalReviewScore(totalScore);

        return dto;
    }
}
