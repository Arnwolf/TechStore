package com.techstore.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;


public class CreateOrderDto {
    public BigDecimal totalAmount;
    public String city;
    public String street;
    public String clientName;
    public String clientPhoneNumber;
    public String clientEmail;
    public LocalDateTime creationDate;
    public int status;

    public Map<Integer, Integer> orderProducts; //<id, quantity>
}
