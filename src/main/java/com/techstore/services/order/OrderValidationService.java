package com.techstore.services.order;

import com.techstore.dto.CreateOrderDto;

public interface OrderValidationService {
    void validate(final CreateOrderDto dto);
}
