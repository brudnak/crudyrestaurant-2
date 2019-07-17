package com.lambdaschool.crudyrestaurants.repos;

import com.lambdaschool.crudyrestaurants.model.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<Menu, Long> {
}
