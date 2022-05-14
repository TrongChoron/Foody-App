package vn.hcmute.nhom02.foody.database;

import java.util.List;

import vn.hcmute.nhom02.foody.Domain.FoodModel;
import vn.hcmute.nhom02.foody.Domain.Restaurant;
import vn.hcmute.nhom02.foody.mapper.FoodMapper;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Fri, 5/13/2022
 * Time     : 22:45
 * Filename : FoodQuery
 */
public class FoodQuery extends AbstractQuery<FoodModel> implements IFoodQuery {
    private static FoodQuery instance = null;
    private IRestaurantQuery restaurantQuery = new RestaurantQuery();

    public static FoodQuery getInstance() {
        if (instance == null) {
            instance = new FoodQuery();
        }
        return instance;
    }

    @Override
    public Long insert(FoodModel foodModel) {
        final String sql = "INSERT INTO food VALUES(null, ?, ?, ?, ?, ?)";
        return insert(sql,  foodModel.getFoodName(), foodModel.getFoodDescription(),
                foodModel.getPrice(),foodModel.getPhotoFood(),foodModel.getRestaurantID());
    }

    @Override
    public Integer update(FoodModel foodModel) {
        final String sql = "UPDATE food SET photo_food = ?, food_name = ?, food_description = ?, price = ?, restaurantID = ? WHERE id = ?";
        return update(sql, foodModel.getPhotoFood(), foodModel.getFoodName(),
                foodModel.getFoodDescription(), foodModel.getPrice(),
                foodModel.getRestaurantID(), foodModel.getId());
    }

    @Override
    public List<FoodModel> findFoodByRestaurant(Integer restaurantID) {
        Restaurant restaurantByID = restaurantQuery.findById(restaurantID);
        if (restaurantByID != null) {
            final String sql = "SELECT * FROM food WHERE restaurantID = " + restaurantByID.getId();
            return query(sql, new FoodMapper());
        }
        return null;
    }

    @Override
    public FoodModel findById(Integer id) {
        final String sql = "SELECT * FROM food WHERE id =" + id;
        return findById(sql, new FoodMapper());
    }

    @Override
    public List<FoodModel> findAll() {
        final String sql = "SELECT * FROM food";
        return query(sql, new FoodMapper());
    }
}
