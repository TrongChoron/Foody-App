package vn.hcmute.nhom02.foody.SortByPrice;

import java.util.List;

import vn.hcmute.nhom02.foody.Domain.FoodModel;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Mon, 6/20/2022
 * Time     : 15:32
 * Filename : Sort
 */
public interface Sort {
    public void sortAscending(List<FoodModel> foodModelList);
    public void sortDescending(List<FoodModel> foodModelList);
}
