package com.lambdaschool.crudyrestaurants.controllers;

import com.lambdaschool.crudyrestaurants.model.Restaurant;
import com.lambdaschool.crudyrestaurants.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping(value = "/restaurants",
            produces = {"application/json"})
    public ResponseEntity<?> listAllRestaurants() {
        List<Restaurant> myRestaurants = restaurantService.findAll();
        return new ResponseEntity<>(myRestaurants, HttpStatus.OK);
    }

    @GetMapping(value = "/restaurant/{restaurantId}",
            produces = {"application/json"})
    public ResponseEntity<?> getRestaurantById(
            @PathVariable
                    long restaurantId) {
        Restaurant r = restaurantService.findRestaurantById(restaurantId);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @GetMapping(value = "/restaurant/name/{name}",
            produces = {"application/json"})
    public ResponseEntity<?> getRestaurantByName(
            @PathVariable
                    String name) {
        Restaurant r = restaurantService.findRestaurantByName(name);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @PostMapping(value = "/restaurant",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewRestaurant(@Valid
                                              @RequestBody
                                                      Restaurant newRestaurant) {
        newRestaurant = restaurantService.save(newRestaurant);

        // set location header to the newly creator resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRestaurantURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{restaurantid}").buildAndExpand(newRestaurant.getRestaurantid()).toUri();
        responseHeaders.setLocation(newRestaurantURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/restaurant/{restaurantid}")
    public ResponseEntity<?> updateRestaurant(
            @RequestBody
                    Restaurant updateRestaurant,
            @PathVariable
                    long restaurantid) {
        restaurantService.update(updateRestaurant, restaurantid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/restaurant/{restaurantid}")
    public ResponseEntity<?> deleteRestaurantById(
            @PathVariable
                    long restaurantid) {
        restaurantService.delete(restaurantid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
