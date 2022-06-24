package vn.hcmute.nhom02.foody.SortByPrice;

import java.util.List;
import java.util.Objects;

import vn.hcmute.nhom02.foody.Domain.FoodModel;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Mon, 6/20/2022
 * Time     : 15:34
 * Filename : QuickSort
 */
public class QuickSort implements Sort{
    @Override
    public void sortAscending(List<FoodModel> foodModelList) {
        int n = foodModelList.size();
        for (int i = 0; i < n - 1; i++) {
            for(int j=0;j<n;j++) {
                if (Objects.requireNonNull((foodModelList.get(i)).getPrice()).compareTo((foodModelList.get(j )).getPrice()) < 0) {
                    swap( foodModelList,i,j);
                }
            }
        }
    }

    @Override
    public void sortDescending(List<FoodModel> foodModelList) {
        int n = foodModelList.size();
        for (int i = 0; i < n - 1; i++) {
            for(int j=0;j<n;j++) {
                if (Objects.requireNonNull((foodModelList.get(i)).getPrice()).compareTo((foodModelList.get(j )).getPrice()) > 0) {
                    swap( foodModelList,i,j);
                }
            }
        }
    }

    private void swap(List<FoodModel> foodModelList, int i, int j){
        FoodModel temp = foodModelList.get(i);
        foodModelList.set(i,foodModelList.get(j));
        foodModelList.set(j,temp);

    }
}
