package com.techstore.entities;

import javax.persistence.*;


@Entity
@Table(name = "users_wishes")
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Product product;


    public Wish() {}
    public Wish(final User user, final Product product) {
        this.user = user;
        this.product = product;
    }


    public Integer getId() { return id; }
    public void setId(final Integer id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(final User user) { this.user = user; }

    public Product getProduct() { return product; }
    public void setProduct(final Product wish) { this.product = wish; }
}
