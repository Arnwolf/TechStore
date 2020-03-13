package com.techstore.components.validator;

import com.techstore.dto.CreateOrderDto;
import com.techstore.services.order.OrdersService;

import java.math.BigDecimal;


public class OrderValidator implements Validator<CreateOrderDto> {

    @Override
    public void validate(final CreateOrderDto dto) {
        if (dto.clientPhoneNumber.isEmpty() || dto.status != OrdersService.OrderStatus.PENDING.status() ||
                dto.clientName.isEmpty() || dto.clientEmail.isEmpty() ||
                dto.city.isEmpty() || dto.street.isEmpty())
            throw new RuntimeException("Fill fields correctly!");
        else if (dto.totalAmount.compareTo(BigDecimal.valueOf(1.0)) < 0)
            throw new RuntimeException("Total amount is invalid!");
    }
}
