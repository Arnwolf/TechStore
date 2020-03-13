package com.techstore.entities;

import javax.persistence.*;


@Entity
@Table(name = "orders_items")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public Integer getId() { return id; }

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    public Order getOrder() { return order; }

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Product product;
    public Product getProduct() { return product; }

    private Integer quantity;
    public Integer getQuantity() { return quantity; }


    public OrderProduct() {}
    public OrderProduct(final Order order, final Product product, final Integer quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }
}
