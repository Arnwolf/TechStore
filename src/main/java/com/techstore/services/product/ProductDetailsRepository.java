package com.techstore.services.product;

import com.techstore.connection.ConnectionManager;
import com.techstore.entities.ProductDetail;
import javax.persistence.EntityManager;
import java.util.Optional;


class ProductDetailsRepository {

    ProductDetail update(final ProductDetail productDetail) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        ProductDetail details = connection.merge(productDetail);

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return details;
    }

    Optional<ProductDetail> findByProductId(final Integer productId) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        final String query = String.format("SELECT pd FROM ProductDetail pd WHERE pd.product=%d", productId);

        Optional<ProductDetail> item = Optional.of(
                connection.createQuery(query, ProductDetail.class).getSingleResult());

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return item;
    }
}
