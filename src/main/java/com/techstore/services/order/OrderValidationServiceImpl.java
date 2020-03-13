package com.techstore.services.order;

import com.techstore.components.validator.OrderValidator;
import com.techstore.components.validator.Validator;
import com.techstore.dto.CreateOrderDto;
import com.techstore.dto.PreviewProductDto;
import com.techstore.services.product.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.List;


public class OrderValidationServiceImpl implements OrderValidationService {
    private Validator<CreateOrderDto> validator = new OrderValidator();

    private static OrderValidationServiceImpl instance = new OrderValidationServiceImpl();
    public static OrderValidationServiceImpl getInstance() { return instance; }

    private OrderValidationServiceImpl() { }

    @Override
    public void validate(final CreateOrderDto dto) {
        validator.validate(dto);

        BigDecimal originalTotalAmount = new BigDecimal(0.0);
        List<PreviewProductDto> products = ProductServiceImpl.getInstance().findByIds(dto.orderProducts.keySet());

        for (PreviewProductDto product : products)
            originalTotalAmount = originalTotalAmount
                    .add(product.price.subtract(product.discount))
                    .multiply(BigDecimal.valueOf(dto.orderProducts.get(product.id)));

        if (dto.totalAmount.compareTo(originalTotalAmount) != 0)
            throw new RuntimeException("Total amount in order does not match original total amount!");
    }
}
