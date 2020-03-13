package com.techstore.services.order;

import com.techstore.dto.CreateOrderDto;
import com.techstore.entities.Order;
import com.techstore.services.product.ProductDetailsService;
import java.time.LocalDateTime;
import java.util.Map;


public class OrdersService {
    private OrderRepository orderRepository = new OrderRepository();

    private static OrdersService instance = new OrdersService();
    public static OrdersService getInstance() { return instance; }

    private static OrderValidationService orderValidationService = OrderValidationServiceImpl.getInstance();


    private OrdersService() { }

    public enum OrderStatus {
        PENDING(0);
        private int code;

        OrderStatus(final int statusCode) { code = statusCode; }

        public int status() { return code; }
    }

    public void createOrder(final CreateOrderDto order) {
        orderValidationService.validate(order);

        Order newOrder = new Order(order.totalAmount,
                order.city,
                order.street,
                order.clientName,
                order.clientPhoneNumber,
                order.clientEmail,
                LocalDateTime.now(),
                OrdersService.OrderStatus.PENDING.status());

        orderRepository.add(newOrder, order.orderProducts);

        ProductDetailsService productDetailsService = ProductDetailsService.getInstance();

        for (final Map.Entry<Integer, Integer> cartElement : order.orderProducts.entrySet())
            productDetailsService.updateSoldTimes(cartElement.getKey(), cartElement.getValue());
    }
}
