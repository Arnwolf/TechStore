package com.techstore.services.order;

import com.techstore.connection.ConnectionManager;
import com.techstore.entities.Order;
import com.techstore.entities.OrderProduct;
import com.techstore.entities.Product;
import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;


class OrderRepository {

    void add(final Order order, final Map<Integer, Integer> productIdsAndQuantities) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Product> products = connection.createQuery(
                String.format("SELECT p FROM Product p WHERE p.id IN (%s)",
                        productIdsAndQuantities.keySet()
                                .stream()
                                .map(String::valueOf)
                                .collect(Collectors.joining(","))),
                Product.class).getResultList();

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

                order.getOrderProducts().add(orderProduct);
            }
        }

        connection.persist(order);

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();
    }
}
