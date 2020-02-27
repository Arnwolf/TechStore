package com.techstore.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    private String street;

    private String city;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "phone_number")
    private String clientPhoneNumber;

    private String email;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    private String status;

    public Order() {}

    public Order(final BigDecimal totalAmount, final String city, final String street,
                 final String clientName, final String clientPhoneNumber, final String email,
                 final LocalDateTime creationDate, final String status) {
        this.totalAmount = totalAmount;
        this.city = city;
        this.status = status;
        this.street = street;
        this.clientName = clientName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.email = email;
        this.creationDate = creationDate;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getClientPhoneNumber() { return clientPhoneNumber; }
    public void setClientPhoneNumber(String clientPhoneNumber) { this.clientPhoneNumber = clientPhoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
