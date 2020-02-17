package com.techstore.services;

import com.techstore.components.ShoppingCart;
import com.techstore.entities.Item;
import com.techstore.entities.Order;
import com.techstore.entities.User;
import com.techstore.repositories.OrderRepository;
import com.techstore.repositories.Repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrdersService {
    private Repository<Order> orderRepository;
    private final static Logger LOG = Logger.getLogger(OrdersService.class.getName());

    private static OrdersService instance;

    public static void init() {
        instance = new OrdersService(new OrderRepository());
    }

    public static OrdersService getInstance() {
        return instance;
    }


    private enum OrderStatus {
        PENDING(1);
        private int code;

        OrderStatus(final int statusCode) {
            code = statusCode;
        }

        public String status() {
            if (code == 1)
                return "pending";
            else
                return null;
        }
    }

    private OrdersService(final Repository<Order> orderRepository) {
        this.orderRepository = orderRepository;
    }

    private void addOrder(final Order newOrder) {
        try {
            orderRepository.add(newOrder);
        } catch (final SQLException exc) {
            exc.printStackTrace();
            LOG.log(Level.ALL, exc.getMessage());
            throw new RuntimeException("SQL Problems");
        }
    }

    public void createOrder(final ShoppingCart cart, final User user) {
        Order newOrder = new Order();
        newOrder.setCity(user.getCity());
        newOrder.setClientName(user.getName());
        newOrder.setClientPhoneNumber(user.getPhoneNumber());
        newOrder.setStreet(user.getStreet());
        newOrder.setEmail(user.getEmail());
        newOrder.setOrderItems(ItemsService.getInstance().getItems(cart.getItems()));
        newOrder.setTotalAmount(cart.getTotalAmount());
        newOrder.setCreationDate(LocalDateTime.now());
        newOrder.setStatus(OrderStatus.PENDING.status());

        Map<String, Integer> quantity = new TreeMap<>();

        for (Item item : newOrder.getOrderItems())
            quantity.put(item.getId(), cart.getQuantity(item.getId()));

        newOrder.setItemsQuantity(quantity);

        addOrder(newOrder);
    }
}
