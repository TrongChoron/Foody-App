package vn.hcmute.nhom02.foody.mapper;

import android.database.Cursor;

import vn.hcmute.nhom02.foody.Domain.FoodModel;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Tue, 5/3/2022
 * Time     : 12:14 AM
 * Filename : FoodMapper
 */
public class FoodMapper implements RowMapper<FoodModel>{
    @Override
    public FoodModel mapRow(Cursor cs) {
        FoodModel food = new FoodModel();
        food.setId(cs.getInt(0));
        food.setFoodName(cs.getString(1));
        food.setFoodDescription(cs.getString(2));
        food.setPrice(cs.getFloat(3));
        food.setPhotoFood(cs.getBlob(4));
        food.setRestaurantID(cs.getInt(5));
        return food;
    }
}
