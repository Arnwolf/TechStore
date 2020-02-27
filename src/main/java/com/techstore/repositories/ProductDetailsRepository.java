package com.techstore.repositories;

import com.techstore.connection.ConnectionManager;
import com.techstore.entities.ProductDetail;
import javax.persistence.EntityManager;
import java.util.List;


public class ProductDetailsRepository {

    public void update(final ProductDetail productDetail) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        connection.merge(productDetail);

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();
    }

    public ProductDetail findByProductId(final Integer productId) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<ProductDetail> items = connection.createQuery(
                String.format("SELECT pd FROM ProductDetail pd WHERE pd.product=%d", productId),
                ProductDetail.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return items.isEmpty() ? null : items.get(0);
    }
}
