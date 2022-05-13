package vn.hcmute.nhom02.foody.mapper;

import android.database.Cursor;

import vn.hcmute.nhom02.foody.Domain.Restaurant;
import vn.hcmute.nhom02.foody.signup.User;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Fri, 5/13/2022
 * Time     : 11:34
 * Filename : RestaurantMapper
 */
public class RestaurantMapper implements RowMapper<Restaurant>{
    @Override
    public Restaurant mapRow(Cursor cs) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(cs.getInt(0));
        restaurant.setName(cs.getString(1));
        restaurant.setAddress(cs.getString(2));
        restaurant.setRestaurantImage(cs.getBlob(3));
        restaurant.setCategoryId(cs.getInt(4));
        return restaurant;
    }
}
