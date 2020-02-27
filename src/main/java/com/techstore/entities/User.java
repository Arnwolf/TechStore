package com.techstore.entities;

import org.codehaus.jackson.annotate.JsonIgnore;
import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @Column(name = "hashed_id")
    private String hashedId = "";

    private String name;

    @JsonIgnore
    private String email;

    @JsonIgnore
    @Column(name = "password")
    private String pass;

    @JsonIgnore
    @Column(name = "phone_number")
    private String phoneNumber = "";

    @JsonIgnore
    private String city = "";

    @JsonIgnore
    private String street = "";

    @JsonIgnore
    private Boolean subscribe = true;


    public User() {}
    public User(final String email, final String name,
                final String password, final Boolean subscribe) {
        this.name = name;
        this.email = email;
        this.pass = password;
        this.subscribe = subscribe;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getID() {
        return id;
    }
    public void setID(Integer id) {
        this.id = id;
    }

    public String getHashedID() {
        return hashedId;
    }
    public void setHashedID(String hashedId) {
        this.hashedId = hashedId;
    }

    public Boolean isSubscribed() {
        return subscribe;
    }
    public void setSubscribed(Boolean subscribed) {
        this.subscribe = subscribed;
    }

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
