package com.techstore.repositories;

import com.techstore.connection.ConnectionManager;
import com.techstore.entities.ProductParameter;
import javax.persistence.EntityManager;
import java.util.List;


public class ParametersRepository {

    public List<ProductParameter> findByProductIdAndName(final Integer itemId, final String productName) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<ProductParameter> itemParameters = connection.createQuery(
                String.format("SELECT pp " +
                        "FROM ProductParameter pp " +
                        "WHERE pp.item=%d OR (pp.item.name='%s' AND pp.categoryParameter.changeable=true)" +
                        "ORDER BY pp.id", itemId, productName),
                ProductParameter.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return itemParameters;
    }
}
