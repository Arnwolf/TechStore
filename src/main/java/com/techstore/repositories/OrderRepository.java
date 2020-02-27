package com.techstore.repositories;

import com.techstore.connection.ConnectionManager;
import com.techstore.entities.Order;
import com.techstore.entities.OrderProduct;
import com.techstore.entities.Product;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;


public class OrderRepository {

    public void add(final Order order, final Map<Integer, Integer> productIdsAndQuantities) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Product> products = connection.createQuery(
                String.format("SELECT p FROM Product p WHERE p.id IN (%s)",
                        productIdsAndQuantities.keySet()
                                .stream()
                                .map(String::valueOf)
                                .collect(Collectors.joining(","))),
                Product.class).getResultList();

        List<OrderProduct> orderProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getAvailability() < productIdsAndQuantities.get(product.getId())) {
                if (connection.getTransaction().isActive()) {
                    connection.getTransaction().rollback();
                    connection.close();
                    throw new RuntimeException("There is no so many items in a warehouse!");
                }
            } else {
                product.setAvailability(product.getAvailability() - productIdsAndQuantities.get(product.getId()));

                OrderProduct orderProduct = new OrderProduct(order, product,
                        productIdsAndQuantities.get(product.getId()));
                orderProducts.add(orderProduct);
            }
        }

        connection.persist(order);

        for (OrderProduct orderProduct : orderProducts)
            connection.persist(orderProduct);

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();
    }

    public void update(final Order entity) { throw new UnsupportedOperationException(); }

    public void remove(final Order entity) { throw new UnsupportedOperationException(); }
}
