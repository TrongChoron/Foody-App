package vn.hcmute.nhom02.foody.database;

import java.util.List;

import vn.hcmute.nhom02.foody.Domain.FoodModel;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Fri, 5/13/2022
 * Time     : 19:51
 * Filename : IFoodQuery
 */
public interface IFoodQuery extends GenericQuery<FoodModel>{
    Long insert(FoodModel foodModel);

    Integer update(FoodModel foodModel);

    List<FoodModel> findFoodByRestaurant(Integer restaurantID);

    FoodModel findById(Integer id);

    List<FoodModel> findAll();

}
