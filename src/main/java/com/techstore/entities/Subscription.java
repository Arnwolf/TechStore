package com.techstore.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;


    public Subscription() {}
    public Subscription(final String email) { this.email = email; }


    public void setId(final Integer id) { this.id = id; }
    public Integer getId() { return id; }

    public void setEmail(final String email) { this.email = email; }
    public String getEmail() { return email; }
}
