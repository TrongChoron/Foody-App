package vn.hcmute.nhom02.foody.Domain;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Wed, 5/11/2022
 * Time     : 23:59
 * Filename : FoodModel
 */
public class FoodModel {
    private Integer id;
    private String foodName;
    private String foodDescription;
    private Float price;
    private byte[] photoFood;
    private Integer restaurantID;

    public FoodModel() {
    }

    public FoodModel(Integer id, String foodName, String foodDescription, Float price, byte[] photoFood, Integer restaurantID) {
        this.id = id;
        this.photoFood = photoFood;
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.price = price;
        this.restaurantID = restaurantID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getPhotoFood() {
        return photoFood;
    }

    public void setPhotoFood(byte[] photoFood) {
        this.photoFood = photoFood;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(Integer restaurantID) {
        this.restaurantID = restaurantID;
    }
}
