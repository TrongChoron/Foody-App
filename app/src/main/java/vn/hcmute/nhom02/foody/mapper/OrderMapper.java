package vn.hcmute.nhom02.foody.mapper;

import android.database.Cursor;

import vn.hcmute.nhom02.foody.Domain.OrderModel;
import vn.hcmute.nhom02.foody.Domain.Restaurant;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Tue, 5/17/2022
 * Time     : 21:12
 * Filename : OrderMapper
 */
public class OrderMapper implements RowMapper<OrderModel> {
    @Override
    public OrderModel mapRow(Cursor cs) {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(cs.getInt(0));
        orderModel.setQuantity(cs.getInt(1));
        orderModel.setStatus(cs.getInt(2));
        orderModel.setFoodID(cs.getInt(3));
        orderModel.setUserId(cs.getInt(4));
        return orderModel;
    }
}
