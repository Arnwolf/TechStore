package com.techstore.services.order;

import com.techstore.dto.CreateOrderDto;
import com.techstore.entities.Order;
import com.techstore.services.product.ProductDetailsService;
import java.util.Map;


public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository = new OrderRepository();

    private static OrderServiceImpl instance = new OrderServiceImpl();
    public static OrderServiceImpl getInstance() { return instance; }

    private static OrderValidationService orderValidationService = OrderValidationServiceImpl.getInstance();


    private OrderServiceImpl() { }

    public void createOrder(final CreateOrderDto order) {
        orderValidationService.validate(order);

        Order newOrder = new Order(order.totalAmount,
                order.city,
                order.street,
                order.clientName,
                order.clientPhoneNumber,
                order.clientEmail,
                order.creationDate,
                order.status);

        orderRepository.add(newOrder, order.orderProducts);

        ProductDetailsService productDetailsService = ProductDetailsService.getInstance();

        for (final Map.Entry<Integer, Integer> cartElement : order.orderProducts.entrySet())
            productDetailsService.updateSoldTimes(cartElement.getKey(), cartElement.getValue());
    }
}
