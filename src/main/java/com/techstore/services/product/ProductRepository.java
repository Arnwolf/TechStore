package com.techstore.services.product;

import com.techstore.connection.ConnectionManager;
import com.techstore.entities.Product;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


class ProductRepository {

    public void add(final Product entity) { throw new UnsupportedOperationException(); }

    public void update(final Product entity) { throw new UnsupportedOperationException(); }

    public void remove(final Product entity) { throw new UnsupportedOperationException(); }

    List<Product> findAllByCategory(final Integer categoryId)
    {
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

    List<Product> findPopular(final boolean onlyAvailable, final boolean onDiscount, final int avgRating, final boolean isNew)
    {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Product> items = connection.createQuery(String.format(
                "SELECT DISTINCT p " +
                        "FROM Review r " +
                        "JOIN Product p ON r.item = p " +
                        "WHERE %s AND (%s r.rating>%d OR p.New=%b)",
                onlyAvailable ? "p.availability>0" : "",
                onDiscount ? "p.discount>0 OR" : "",
                avgRating,
                isNew), Product.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return items;
    }

    public Product findById(final Integer productId)
    {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        final Product item = connection.find(Product.class, productId);

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return item;
    }

    Product findDetailedById(final Integer productId)
    {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        final Product item = connection.find(Product.class, productId);

        Hibernate.initialize(item.getReviews());
        Hibernate.initialize(item.getParameters());
        Hibernate.initialize(item.getPhotos());

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return item;
    }

    List<Product> findByIds(final Collection<Integer> ids)
    {
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

    List<Product> findByName(final String productName)
    {
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

    List<Product> findByCategoryParameterIdAndParameterValue(final Integer categoryParameterId, final String parameterValue) {
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

    List<Product> findByNew()
    {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Product> items = connection.createQuery("SELECT p FROM Product p WHERE p.New=true",
                Product.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return items;
    }

    List<Product> findByDiscount()
    {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Product> items = connection.createQuery("SELECT p FROM Product p WHERE p.discount>0",
                Product.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return items;
    }

    List<Product> findByRating(final int avgRating) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Product> items = connection.createQuery(
                String.format("SELECT p FROM Review r JOIN Product p ON r.item = p WHERE r.rating>%d", avgRating),
                Product.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return items;
    }
}
