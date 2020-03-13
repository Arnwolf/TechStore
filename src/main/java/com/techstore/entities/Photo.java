package com.techstore.entities;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;


@Entity
@Table(name = "item_photos")
@Immutable
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public Integer getId() { return id; }

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Product product;
    public Product getProduct() { return product; }

    private String photo;
    public String getPhoto() { return photo; }


    public Photo() {}
    public Photo(final Product product, final String photo) {
        this.photo = photo;
        this.product = product;
    }
}
