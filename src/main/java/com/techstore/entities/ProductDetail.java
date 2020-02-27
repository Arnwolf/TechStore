package com.techstore.entities;

import javax.persistence.*;


@Entity
@Table(name = "item_details")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "viewed_times")
    private Integer viewedTimes;

    @Column(name = "sold_times")
    private  Integer soldTimes;

    @OneToOne
    @JoinColumn(name = "item_id", unique = true)
    private Product product;


    public ProductDetail() { }
    public ProductDetail(final Integer viewedTimes, final Integer soldTimes, final Product product) {
        this.viewedTimes = viewedTimes;
        this.soldTimes = soldTimes;
        this.product = product;
    }


    public Integer getId() { return id; }
    public void setId(final Integer id) { this.id = id; }

    public Integer getViewedTimes() { return viewedTimes; }
    public void setViewedTimes(final Integer viewedTimes) { this.viewedTimes = viewedTimes; }

    public Integer getSoldTimes() { return soldTimes; }
    public void setSoldTimes(final Integer soldTimes) { this.soldTimes = soldTimes; }

    public Product getProduct() {return product; }
    public void setProduct(final Product product) {this.product = product; }
}
