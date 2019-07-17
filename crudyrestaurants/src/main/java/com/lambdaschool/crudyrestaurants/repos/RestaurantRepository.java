package com.lambdaschool.crudyrestaurants.repos;

import com.lambdaschool.crudyrestaurants.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    Restaurant findByName(String name);
}
