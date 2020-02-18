package com.techstore.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Order {
    private String id;
    private BigDecimal totalAmount;
    private String street;
    private String city;
    private String clientName;
    private String clientPhoneNumber;
    private List<String> orderItemsIds;
    private Map<String, Integer> itemsQuantity;
    private String email;
    private LocalDateTime creationDate;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public List<String> getOrderItemsIds() {
        return orderItemsIds;
    }

    public void setOrderItems(List<String> orderItemsIds) {
        this.orderItemsIds = orderItemsIds;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Integer> getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(Map<String, Integer> itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }
}
