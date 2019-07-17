package com.lambdaschool.crudyrestaurants.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long menuid;

    @Column(nullable = false)
    private String dish;
    private double price;


    // !!! must match mapped by in restaurant !!!
    @ManyToOne
    @JoinColumn(name = "restaurantid",
            nullable = false)

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
    @JsonIgnoreProperties("menus")
    private Restaurant restaurant; // ends up with foreign key

    // why Spring requires default 10:08
    public Menu() {
    }

    public Menu(String dish, double price, Restaurant restaurant) {
        this.dish = dish;
        this.price = price;
        this.restaurant = restaurant;
    }

    public long getMenuid() {
        return menuid;
    }

    public void setMenuid(long menuid) {
        this.menuid = menuid;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
