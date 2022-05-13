package vn.hcmute.nhom02.foody.Domain;

import androidx.annotation.NonNull;

import java.util.Arrays;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Fri, 5/13/2022
 * Time     : 10:30
 * Filename : Restaurant
 */
public class Restaurant {
    private Integer id;
    private String name;
    private String address;
    private byte[] restaurantImage;
    private Integer categoryId;

    public Restaurant(){

    }

    public Restaurant(Integer id, String name, String address, byte[] restaurantImage, Integer categoryId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.restaurantImage = restaurantImage;
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public byte[] getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(byte[] restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

    @NonNull
    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", restaurantImage=" + Arrays.toString(restaurantImage) +
                ", categoryId=" + categoryId +
                '}';
    }
}
