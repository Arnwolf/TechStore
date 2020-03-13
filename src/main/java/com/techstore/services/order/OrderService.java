package com.techstore.services.order;

import com.techstore.dto.CreateOrderDto;

public interface OrderService {
    void createOrder(final CreateOrderDto order);

    enum OrderStatus {
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
}
