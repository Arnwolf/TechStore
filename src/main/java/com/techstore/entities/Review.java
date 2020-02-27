package com.techstore.entities;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "item_reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String description;

    private Integer rating;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Product item;


    public Review() {}
    public Review(final User user, final Product item, final String description,
                  final Integer rating,
                  final LocalDateTime creationDate) {
        this.user = user;
        this.item = item;
        this.description = description;
        this.rating = rating;
        this.creationDate = creationDate;
    }


    public Integer getId() { return id; }
    public void setId(final Integer id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(final User user) { this.user = user; }

    public String getDescription() { return description; }
    public void setDescription(final String description) {
        this.description = description;
    }

    public Integer getRating() { return rating; }
    public void setRating(final Integer rating) {
        this.rating = rating;
    }

    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(final LocalDateTime creationDate) { this.creationDate = creationDate; }

    public Product getItem() { return item; }
    public void setItem(final Product item) { this.item = item; }

    @Override
    public String toString() {
        return String.format("Review { " +
                "Review ID: %d" +
                "User Name: %s" +
                "User ID: %d" +
                "Rating: %d" +
                "Description: %s }", id, user.getName(), user.getID(), rating, description);
    }
}
