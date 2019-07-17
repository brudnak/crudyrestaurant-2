package com.lambdaschool.crudyrestaurants;

import com.lambdaschool.crudyrestaurants.model.Menu;
import com.lambdaschool.crudyrestaurants.model.Restaurant;
import com.lambdaschool.crudyrestaurants.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import javax.transaction.Transactional;


@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RestaurantService restaurantService;


    @Override
    public void run(String[] args) throws Exception
    {
        // Restaurant String name, String address, String city, String state, String telephone
        String rest1Name = "Apple";
        Restaurant r1 = new Restaurant(rest1Name,
                "123 Main Street",
                "City", "ST", "555-555-1234");
        r1.getMenus().add(new Menu("Mac and Cheese", 6.95, r1));
        r1.getMenus().add(new Menu("Lasagna", 8.50, r1));
        r1.getMenus().add(new Menu("Meatloaf", 7.77, r1));
        r1.getMenus().add(new Menu("Tacos", 8.49, r1));
        r1.getMenus().add(new Menu("Chef Salad", 12.50, r1));

        restaurantService.save(r1);

        String rest2Name = "Eagle Cafe";
        Restaurant r2 = new Restaurant(rest2Name,
                "321 Uptown Drive",
                "Town", "ST", "555-555-5555");
        r2.getMenus().add(new Menu("Tacos", 10.49, r2));
        r2.getMenus().add(new Menu("Barbacoa", 12.75, r2));

        restaurantService.save(r2);

        String rest3Name = "Number 1 Eats";
        Restaurant r3 = new Restaurant(rest3Name,
                "565 Side Avenue",
                "Village", "ST", "555-123-1555");
        r3.getMenus().add(new Menu("Pizza", 15.15, r3));

        restaurantService.save(r3);
    }
}
