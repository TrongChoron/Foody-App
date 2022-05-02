package vn.hcmute.nhom02.foody.mapper;

import android.database.Cursor;

import vn.hcmute.nhom02.foody.Domain.Food;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Tue, 5/3/2022
 * Time     : 12:14 AM
 * Filename : FoodMapper
 */
public class FoodMapper implements RowMapper{
    @Override
    public Object mapRow(Cursor cs) {
        Food food = new Food();
        food.setId(cs.getInt(0));
        food.setName(cs.getString(1));
        food.setPic(cs.getString(2));
        food.setPrice(cs.getString(3));
        food.setType(cs.getString(4));
        return food;
    }
}
