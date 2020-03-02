package com.techstore.services;

import com.techstore.components.ShoppingCart;
import com.techstore.entities.Order;
import com.techstore.repositories.OrderRepository;
import java.util.Map;


public class OrdersService {
    private OrderRepository orderRepository = new OrderRepository();

    private static OrdersService instance = new OrdersService();
    public static OrdersService getInstance() { return instance; }

    private OrdersService() { }

    private enum OrderStatus {
        PENDING(0);
        private int code;

        OrderStatus(final int statusCode) { code = statusCode; }

        public String status() {
            if (code == 0)
                return "pending";
            else
                return "";
        }
    }

    public void createOrder(final ShoppingCart cart, final Order order) {
        order.setTotalAmount(cart.getTotalAmount());
        order.setStatus(OrderStatus.PENDING.status());

        orderRepository.add(order, cart.getCart());

        ProductDetailsService productDetailsService = ProductDetailsService.getInstance();

        for (final Map.Entry<Integer, Integer> cartElement : cart.getCart().entrySet())
            productDetailsService.updateSoldTimes(cartElement.getKey(), cartElement.getValue());
    }
}
