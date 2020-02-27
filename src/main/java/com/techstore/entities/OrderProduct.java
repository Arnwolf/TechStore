package com.techstore.entities;

import javax.persistence.*;


@Entity
@Table(name = "orders_items")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Product product;

    private Integer quantity;


    public OrderProduct() {}
    public OrderProduct(final Order order, final Product product, final Integer quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }


    public void setId(final Integer id) { this.id = id; }
    public Integer getId() { return id; }

    public void setOrder(final Order order) { this.order = order; }
    public Order getOrder() { return order; }

    public void setProduct(final Product product) { this.product = product; }
    public Product getProduct() { return product; }

    public void setQuantity(final Integer quantity) { this.quantity = quantity; }
    public Integer getQuantity() { return quantity; }
}
