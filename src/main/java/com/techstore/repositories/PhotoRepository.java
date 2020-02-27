package com.techstore.repositories;

import com.techstore.connection.ConnectionManager;
import com.techstore.entities.Photo;
import javax.persistence.EntityManager;
import java.util.List;


public class PhotoRepository {

    public List<Photo> findByProductId(final Integer productId) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Photo> photos = connection.createQuery(
                String.format("SELECT p FROM Photo p WHERE p.product=%s", productId),
                Photo.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return photos;
    }
}
