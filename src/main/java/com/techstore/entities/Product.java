package com.techstore.entities;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "items")
@Immutable
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public Integer getId() { return id; }

    private String manufacturer;
    public String getManufacturer() { return manufacturer; }

    private String name;
    public String getName() { return name; }

    private BigDecimal price;
    public BigDecimal getPrice() { return price; }

    @Column(name = "main_photo")
    private String mainPhoto;
    public String getMainPhoto() { return mainPhoto; }

    private BigDecimal discount;
    public BigDecimal getDiscount() { return discount; }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    public Category getCategory() { return category; }

    private Integer availability;
    public Integer getAvailability() { return availability; }
    public void setAvailability(final int availability) { this.availability = availability; }

    @Column(name = "new")
    private Boolean New;
    public Boolean isNew() { return New; }

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    @OrderBy(value = "categoryParameter")
    private Set<ProductParameter> parameters = new HashSet<>();
    public Set<ProductParameter> getParameters() { return parameters; }

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    @OrderBy(value = "creationDate DESC ")
    private Set<Review> reviews = new HashSet<>();
    public Set<Review> getReviews() { return reviews; }

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @OrderBy(value = "id")
    private Set<Photo> photos = new HashSet<>();
    public Set<Photo> getPhotos() { return photos; }


    public Product() {}

    public class Builder {
        public Product build() { return Product.this; }

        public Builder addName(final String name) {
            Product.this.name = name;
            return this;
        }

        public Builder addManufacturer(final String manufacturer) {
            Product.this.manufacturer = manufacturer;
            return this;
        }

        public Builder addPrice(final BigDecimal price) {
            Product.this.price = price;
            return this;
        }

        public Builder addPhoto(final String photo) {
            mainPhoto = photo;
            return this;
        }

        public Builder addDiscount(final BigDecimal discount) {
            Product.this.discount = discount;
            return this;
        }

        public Builder addCategory(final Category category) {
            Product.this.category = category;
            return this;
        }

        public Builder addAvailability(final int availability) {
            Product.this.availability = availability;
            return this;
        }

        public Builder addNew(final boolean isNew) {
            New = isNew;
            return this;
        }

        public Builder addParameters(final Set<ProductParameter> parameters) {
            Product.this.parameters = parameters;
            return this;
        }

        public Builder addPhotos(final Set<Photo> photos) {
            Product.this.photos = photos;
            return this;
        }
    }


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
