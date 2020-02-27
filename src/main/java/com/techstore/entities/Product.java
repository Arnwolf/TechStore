package com.techstore.entities;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "items")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String manufacturer;

    private String name;

    private BigDecimal price;

    @Column(name = "main_photo")
    private String mainPhoto;

    private BigDecimal discount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Integer availability;

    @Column(name = "new")
    private Boolean New;


    public void setId(final Integer id) { this.id = id; }
    public Integer getId() { return id; }

    public void setManufacturer(final String manufacturer) { this.manufacturer = manufacturer; }
    public String getManufacturer() { return manufacturer; }

    public void setName(final String name) { this.name = name; }
    public String getName() { return name; }

    public void setPrice(final BigDecimal price) { this.price = price; }
    public BigDecimal getPrice() { return price; }

    public void setMainPhoto(final String mainPhoto) { this.mainPhoto = mainPhoto; }
    public String getMainPhoto() { return mainPhoto; }

    public void setDiscount(final BigDecimal discount) { this.discount = discount; }
    public BigDecimal getDiscount() { return discount; }

    public void setCategory(final Category category) { this.category = category; }
    public Category getCategory() { return category; }

    public void setAvailability(final Integer availability) { this.availability = availability; }
    public Integer getAvailability() { return availability; }

    public void setNew(final Boolean New) { this.New = New; }
    public Boolean isNew() { return New; }


    public Product() {}


    @Override
    public String toString() {
        return String.format(
                " ID : %s " +
                " Name : %s " +
                " Price : %f " +
                " CategoryID : %s " +
                " Discount : %f " +
                " Availability : %d " +
                " MainPhoto : %s", id, manufacturer + name, price, category.getId(), discount, availability, mainPhoto);
    }
}
