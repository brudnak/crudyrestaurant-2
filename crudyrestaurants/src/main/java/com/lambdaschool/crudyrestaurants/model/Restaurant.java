package com.lambdaschool.crudyrestaurants.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long restaurantid;

    @Column(unique = true,
            nullable = false)
    private String name;

    private String address;
    private String city;
    private String state;
    private String telephone;

    // Spring data requires generic List<Menu>
    // time of explanation on instructors computer
    // around Wed 9:57 AM - was not done this way for WEB19
    // WEB20 recording
    @OneToMany(mappedBy = "restaurant",
            // name restaurant must match in Menu model
            cascade = CascadeType.ALL,
            orphanRemoval = true)

    // When you have a OneToMany relationship you can get into an infinite loop.
    // Say you print a Restaurant which prints the Menu items for that restaurant
    // but Menu items prints the restaurant they are associated with. So we print
    // the Restaurant again which prints the Menu items for that restaurant but again
    // Menu items prints the restaurant they are associate with. So we print the
    // Restaurant and go on and on.

    // We need to break the chain. So in a OneToMany relationship, we don’t want the many
    // part to also print the one part. So we want to prevent the menu items from printing
    // the restaurants. We use an annotation called JsonIgnoreProperties. So in our
    // Restaurant model we need to tell our menu items list not print the restaurant.
    // We do this by adding @JsonIgnoreProperties(“restaurant”) right above
    // private List<Menu> menus = new ArrayList<>();

    // !!! menus SPECIFIC !!!
    // The same issue can happen when printing when working with Menus on the Many side so
    // we need something similar in Menu.java
    @JsonIgnoreProperties("restaurant")
    private List<Menu> menus = new ArrayList<>();

    public Restaurant() {
    }

    public Restaurant(String name, String address, String city, String state, String telephone) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.telephone = telephone;
    }

    public long getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(long restaurantid) {
        this.restaurantid = restaurantid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
