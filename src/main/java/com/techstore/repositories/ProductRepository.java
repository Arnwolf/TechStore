package com.techstore.repositories;

import com.techstore.connection.ConnectionManager;
import com.techstore.entities.Product;
import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class ProductRepository {

    public void add(final Product entity) { throw new UnsupportedOperationException(); }

    public void update(final Product entity) { throw new UnsupportedOperationException(); }

    public void remove(final Product entity) { throw new UnsupportedOperationException(); }

    public List<Product> findAllByCategory(final Integer categoryId) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Product> items = connection.createQuery(
                String.format("SELECT p FROM Product p WHERE p.category=%s", categoryId),
                Product.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return items;
    }

    public List<Product> findPopular() {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Product> items = connection.createQuery("SELECT p FROM Product p " +
                        "JOIN Review r ON r.item = p " +
                        "WHERE p.availability>0 AND (p.discount>0 OR r.rating>3 OR p.New=true)" +
                        "GROUP BY p.id",
                Product.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return items;
    }

    public Product findById(final Integer productId) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        final Product item = connection.find(Product.class, productId);

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return item;
    }

    public List<Product> findByIds(final Collection<Integer> ids) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Product> items = connection.createQuery(
                String.format("SELECT p FROM Product p WHERE p.id IN (%s)",
                        ids.stream()
                                .map(String::valueOf)
                                .collect(Collectors.joining(","))),
                Product.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return items;
    }

    public List<Product> findByName(final String productName) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Product> items = connection.createQuery(
                String.format("SELECT p FROM Product p WHERE CONCAT(p.manufacturer, ' ', p.name) LIKE '%s%%' " +
                        "OR CONCAT(p.manufacturer, ' ', p.Name) LIKE '%%%s'", productName, productName),
                Product.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return items;
    }

    public List<Product> findByCategoryParameterIdAndParameterValue(final Integer categoryParameterId, final String parameterValue) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Product> items = connection.createQuery(
                String.format("SELECT p " +
                        "FROM ProductParameter pp " +
                        "JOIN Product p ON pp.item=p " +
                        "WHERE pp.categoryParameter=%d AND pp.value='%s'", categoryParameterId, parameterValue),
                Product.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return items;
    }
}
