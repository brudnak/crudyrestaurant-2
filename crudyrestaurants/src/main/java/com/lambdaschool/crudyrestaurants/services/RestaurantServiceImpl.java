package com.lambdaschool.crudyrestaurants.services;

import com.lambdaschool.crudyrestaurants.model.Menu;
import com.lambdaschool.crudyrestaurants.model.Restaurant;
import com.lambdaschool.crudyrestaurants.repos.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

// !!! PICK transactional FROM Spring framework !!!
@Transactional
@Service(value = "restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    // Autowire - explanation at 10:32AM WEB20
    @Autowired
    private RestaurantRepository restrepos;

    @Override
    public List<Restaurant> findAll() {
        List<Restaurant> list = new ArrayList<>();

        restrepos.findAll().iterator().forEachRemaining(list::add);

        // "list" - is a normal ArrayList at this point

        return list;
    }

    @Override
    public Restaurant findRestaurantById(long id) {
        return restrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public Restaurant findRestaurantByName(String name) {

        Restaurant restaurant = restrepos.findByName(name);
        if (restaurant == null) {
            throw new EntityNotFoundException("Restaurant " + name + " not found!");
        }
        return restaurant;
    }

    // !!! PICK transactional FROM Spring framework !!!
    @Transactional
    @Override
    public void delete(long id) {

        if (restrepos.findById(id).isPresent()) {
            restrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    // !!! PICK transactional FROM Spring framework !!!
    @Transactional
    @Override
    public Restaurant save(Restaurant restaurant) {

        Restaurant newRestaurant = new Restaurant();

        newRestaurant.setName(restaurant.getName());
        newRestaurant.setAddress(restaurant.getAddress());
        newRestaurant.setCity(restaurant.getCity());
        newRestaurant.setState(restaurant.getState());
        newRestaurant.setTelephone(restaurant.getTelephone());

        // !!! WRONG !!! do not use
        // newRestaurant.setMenus(restaurant.getMenus());

        // right way
        for (Menu m : restaurant.getMenus()) {
            newRestaurant.getMenus().add(new Menu(m.getDish(), m.getPrice(), newRestaurant));
        }

        return restrepos.save(newRestaurant);
    }

    // !!! PICK transactional FROM Spring framework !!!
    @Transactional
    @Override
    public Restaurant update(Restaurant restaurant, long id) {

        Restaurant currentRestaurant = restrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        if (restaurant.getName() != null) {
            currentRestaurant.setName(restaurant.getName());
        }
        if (restaurant.getAddress() != null) {
            currentRestaurant.setAddress(restaurant.getAddress());
        }
        if (restaurant.getCity() != null) {
            currentRestaurant.setCity(restaurant.getCity());
        }
        if (restaurant.getState() != null) {
            currentRestaurant.setState(restaurant.getState());
        }
        if (restaurant.getTelephone() != null) {
            currentRestaurant.setTelephone((restaurant.getTelephone()));
        }

        // adds new menu items
        if (restaurant.getMenus().size() > 0) {
            for (Menu m : restaurant.getMenus()) {
                currentRestaurant.getMenus().add(new Menu(m.getDish(), m.getPrice(), currentRestaurant));
            }
        }
        return restrepos.save((currentRestaurant));
    }
}
