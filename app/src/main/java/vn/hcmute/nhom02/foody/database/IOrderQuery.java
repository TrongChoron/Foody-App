package vn.hcmute.nhom02.foody.database;

import java.util.List;

import vn.hcmute.nhom02.foody.Domain.FoodModel;
import vn.hcmute.nhom02.foody.Domain.OrderModel;
import vn.hcmute.nhom02.foody.Domain.Restaurant;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Tue, 5/17/2022
 * Time     : 21:16
 * Filename : IOrderQuery
 */
public interface IOrderQuery extends GenericQuery<OrderModel>{
    Long insert(OrderModel orderModel);

    Integer update(OrderModel orderModel);

    List<OrderModel> findOrderByUserId(Integer userId);

    OrderModel findById(Integer id);

    Integer updateQuantity(OrderModel orderModel);

    List<OrderModel> findAll();

    Integer deleteOrderByUser(Integer userId);

    Integer deleteOrder(Integer id);

    OrderModel findByFoodAndUserId(Integer foodID,Integer userID);

    OrderModel findByFoodAndUserAndStatus(Integer foodID,Integer userID,Integer status);

    Integer deleteOrderByFood(Integer foodId);
}
