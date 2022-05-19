package vn.hcmute.nhom02.foody.database;

import java.util.List;

import vn.hcmute.nhom02.foody.Domain.FoodModel;
import vn.hcmute.nhom02.foody.Domain.OrderModel;
import vn.hcmute.nhom02.foody.mapper.FoodMapper;
import vn.hcmute.nhom02.foody.mapper.OrderMapper;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Tue, 5/17/2022
 * Time     : 21:16
 * Filename : OrderQuery
 */
public class OrderQuery extends AbstractQuery<OrderModel> implements IOrderQuery {
    private static IOrderQuery instance = null;

    public static IOrderQuery getInstance() {
        if (instance == null) {
            instance = new OrderQuery();
        }
        return instance;
    }
    @Override
    public Long insert(OrderModel orderModel) {
        final String sql = "INSERT INTO orders VALUES(null, ?, ?, ?, ?)";
        return insert(sql,  orderModel.getQuantity(), orderModel.getStatus(),
                orderModel.getFoodID(),orderModel.getUserId());
    }

    @Override
    public Integer update(OrderModel orderModel) {
        final String sql = "UPDATE orders SET quantity = ?, status = ?, food_id = ?, user_id = ? WHERE id = ?";
        return update(sql, orderModel.getQuantity(), orderModel.getStatus(),
                orderModel.getFoodID(), orderModel.getUserId(),
                orderModel.getId());
    }

    @Override
    public List<OrderModel> findOrderByUserId(Integer userId) {
        final String sql = "SELECT * FROM orders WHERE user_id = " + userId;
        return query(sql, new OrderMapper());
    }

    @Override
    public OrderModel findById(Integer id) {
        final String sql = "SELECT * FROM orders WHERE id =" + id;
        return findById(sql, new OrderMapper());
    }

    @Override
    public Integer updateQuantity(OrderModel orderModel) {
        final String sql ="UPDATE orders SET quantity = ? WHERE id = ?";
        return update(sql,orderModel.getQuantity(),orderModel.getId());
    }

    @Override
    public List<OrderModel> findAll() {
        final String sql = "SELECT * FROM orders";
        List<OrderModel> orderModels = query(sql, new OrderMapper());
        return query(sql, new OrderMapper());
    }

    @Override
    public Integer deleteOrderByUser(Integer userId) {
        final String sql =  "DELETE FROM orders WHERE user_id = ?";
        return delete(sql,userId);
    }

    @Override
    public Integer deleteOrder(Integer id) {
        final String sql = "DELETE FROM orders WHERE id = ?";
        return delete(sql, id);
    }

    @Override
    public OrderModel findByFoodAndUserId(Integer foodID, Integer userID) {
        final String sql = "SELECT * FROM orders WHERE food_id = " + foodID + " and" +
                " user_id = " + userID;
        final List<OrderModel> orderModels = query(sql, new OrderMapper());
        return orderModels.size() > 0 ? orderModels.get(0) : null;
    }

    @Override
    public OrderModel findByFoodAndUserAndStatus(Integer foodID, Integer userID, Integer status) {
        final String sql = "SELECT * FROM orders WHERE food_id = " + foodID + " and" +
                " user_id = " + userID+ " and status = "+status;
        final List<OrderModel> orderModels = query(sql, new OrderMapper());
        return orderModels.size() > 0 ? orderModels.get(0) : null;
    }

    @Override
    public Integer deleteOrderByFood(Integer foodId) {
        final String sql =  "DELETE FROM orders WHERE food_id = ?";
        return delete(sql,foodId);
    }
}
