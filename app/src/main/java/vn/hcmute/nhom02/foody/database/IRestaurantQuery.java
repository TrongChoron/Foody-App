package vn.hcmute.nhom02.foody.database;

import java.util.List;

import vn.hcmute.nhom02.foody.Domain.Restaurant;
import vn.hcmute.nhom02.foody.signup.User;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Fri, 5/13/2022
 * Time     : 11:55
 * Filename : IRestaurantQuery
 */
public interface IRestaurantQuery extends GenericQuery<Restaurant>{
    Long insert(Restaurant restaurant);

    Integer updateRestaurant(Restaurant restaurant);

    Restaurant findById(Integer id);

    Restaurant findByName(String name);

    List<Restaurant> findByCategory(Integer categoryId);

    Integer deleteRestaurant(Restaurant restaurant);
}
