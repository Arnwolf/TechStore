package com.techstore.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;


@Entity
@Table(name = "users_wishes")
@DynamicUpdate
@Immutable
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public Integer getId() { return id; }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public User getUser() { return user; }

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Product product;
    public Product getProduct() { return product; }


    public Wish() {}
    public Wish(final User user, final Product product) {
        this.user = user;
        this.product = product;
    }
}
