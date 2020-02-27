package com.techstore.entities;

import javax.persistence.*;


@Entity
@Table(name = "item_photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Product product;

    private String photo;


    public Photo() {}
    public Photo(final Product product, final String photo) {
        this.photo = photo;
        this.product = product;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }
}
