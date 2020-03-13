package com.techstore.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public Integer getId() { return id; }

    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    public BigDecimal getTotalAmount() { return totalAmount; }

    private String street;
    public String getStreet() { return street; }

    private String city;
    public String getCity() { return city; }

    @Column(name = "client_name")
    private String clientName;
    public String getClientName() { return clientName; }

    @Column(name = "phone_number")
    private String clientPhoneNumber;
    public String getClientPhoneNumber() { return clientPhoneNumber; }

    private String email;
    public String getEmail() { return email; }

    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    public LocalDateTime getCreationDate() { return creationDate; }

    private String status;
    public String getStatus() { return status; }
    public void setStatus(final String status) { this.status = status; }

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<OrderProduct> orderProducts = new ArrayList<>();
    public List<OrderProduct> getOrderProducts() { return orderProducts; }

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
}
