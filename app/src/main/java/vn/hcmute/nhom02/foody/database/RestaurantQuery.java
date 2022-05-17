package vn.hcmute.nhom02.foody.database;

import java.util.List;

import vn.hcmute.nhom02.foody.Domain.Restaurant;
import vn.hcmute.nhom02.foody.mapper.RestaurantMapper;
import vn.hcmute.nhom02.foody.mapper.UserMapper;
import vn.hcmute.nhom02.foody.signup.User;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Fri, 5/13/2022
 * Time     : 11:59
 * Filename : RestaurantQuery
 */
public class RestaurantQuery extends AbstractQuery<Restaurant> implements IRestaurantQuery{

    private static RestaurantQuery instance = null;

    public static RestaurantQuery getInstance() {
        if (instance == null) {
            instance = new RestaurantQuery();
        }
        return instance;
    }
    @Override
    public Long insert(Restaurant restaurant) {
        final String sql = "INSERT INTO restaurant VALUES(null, ?, ?, ?, ?)";
        return insert(sql, restaurant.getName(), restaurant.getAddress(), restaurant.getRestaurantImage(),restaurant.getCategoryId());

    }

    @Override
    public Integer updateRestaurant(Restaurant restaurant) {
        final String sql = "UPDATE restaurant SET name = ?, address = ?, pic = ?, categoryID = ? WHERE id = ?";
        return update(sql, restaurant.getName(), restaurant.getAddress(),restaurant.getRestaurantImage()
                ,restaurant.getCategoryId(),restaurant.getId());
    }

    @Override
    public Restaurant findById(Integer id) {
        final String sql = "SELECT * FROM restaurant WHERE id = " + id;
        return findById(sql, new RestaurantMapper());
    }

    @Override
    public Restaurant findByName(String name) {
        final String sql = "SELECT * FROM restaurant WHERE name = " + name;
        return findById(sql, new RestaurantMapper());
    }

    @Override
    public List<Restaurant> findByCategory(Integer categoryId) {
        final String sql = "SELECT * FROM restaurant WHERE categoryID = " + categoryId + " ";
        List<Restaurant> results = query(sql, new RestaurantMapper());
        return results.size() > 0 ? results : null;
    }

    @Override
    public Integer deleteRestaurant(Restaurant restaurant) {
        final String sql = "DELETE FROM restaurant WHERE id = ?";
        return delete(sql,restaurant.getId());
    }
}
