package com.techstore.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "hashed_id")
    private String hashedId = "";

    private String name;

    private String email;

    @Column(name = "password")
    private String pass;

    @Column(name = "phone_number")
    private String phoneNumber = "";

    private String city = "";

    private String street = "";

    private Boolean subscribe = true;


    public User() {}
    public User(final String email, final String name,
                final String password, final Boolean subscribe) {
        this.name = name;
        this.email = email;
        this.pass = password;
        this.subscribe = subscribe;
    }


    public String getName() { return name; }
    public void setName(final String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(final String email) { this.email = email; }

    public String getPass() { return pass; }
    public void setPass(final String pass) { this.pass = pass; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(final String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getCity() { return city; }
    public void setCity(final String city) { this.city = city; }

    public String getStreet() { return street; }
    public void setStreet(final String street) { this.street = street; }

    public Integer getId() { return id; }
    public void setId(final Integer id) { this.id = id; }

    public String getHashedID() { return hashedId; }
    public void setHashedID(final String hashedId) { this.hashedId = hashedId; }

    public Boolean isSubscribed() { return subscribe; }
    public void setSubscribed(final Boolean subscribed) { this.subscribe = subscribed; }

    @Override
    public String toString() {
        return String.format("User { " +
                "ID: %d, " +
                "Name: %s, " +
                "Password: %s, " +
                "Email: %s, " +
                "City: %s, " +
                "Street: %s, " +
                "Phone Number: %s, " +
                "Subscribe: %b }", id, name, pass, email, city, street, phoneNumber, subscribe);
    }
}
