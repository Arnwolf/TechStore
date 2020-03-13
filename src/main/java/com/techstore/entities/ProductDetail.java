package com.techstore.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;


@Entity
@Table(name = "item_details")
@DynamicInsert
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public Integer getId() { return id; }

    @Column(name = "viewed_times")
    private Integer viewedTimes;
    public Integer getViewedTimes() { return viewedTimes; }
    public void setViewedTimes(final int viewedTimes) { this.viewedTimes = viewedTimes; }

    @Column(name = "sold_times")
    private  Integer soldTimes;
    public Integer getSoldTimes() { return soldTimes; }
    public void setSoldTimes(final int soldTimes) { this.soldTimes = soldTimes; }

    @OneToOne
    @JoinColumn(name = "item_id", unique = true)
    private Product product;
    public Product getProduct() {return product; }


    public ProductDetail() { }
    public ProductDetail(final Integer viewedTimes, final Integer soldTimes, final Product product) {
        this.viewedTimes = viewedTimes;
        this.soldTimes = soldTimes;
        this.product = product;
    }
}
