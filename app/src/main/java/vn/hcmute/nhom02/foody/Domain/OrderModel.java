package vn.hcmute.nhom02.foody.Domain;

import java.io.Serializable;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Tue, 5/17/2022
 * Time     : 21:04
 * Filename : OrderModel
 */
public class OrderModel implements Serializable {
    private Integer id;
    private Integer quantity;
    private Integer status;
    private Integer foodID;
    private Integer userId;

    public OrderModel (){

    }

    public OrderModel(Integer id, Integer quantity, Integer status, Integer foodID, Integer userId) {
        this.id = id;
        this.quantity = quantity;
        this.status = status;
        this.foodID = foodID;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFoodID() {
        return foodID;
    }

    public void setFoodID(Integer foodID) {
        this.foodID = foodID;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void receive(String info){
        System.out.println("Receiving information from request....");
        System.out.println(info);
    }
}
