package com.techstore.services.product;

import com.techstore.connection.ConnectionManager;
import com.techstore.entities.ProductParameter;
import javax.persistence.EntityManager;
import java.util.List;


class ParametersRepository {

    List<ProductParameter> findChangeableByName(final String productName) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<ProductParameter> itemParameters = connection.createQuery(
                String.format("SELECT pp " +
                        "FROM ProductParameter pp " +
                        "WHERE pp.item.name='%s' AND pp.categoryParameter.changeable=true " +
                        "ORDER BY pp.id", productName),
                ProductParameter.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return itemParameters;
    }
}
