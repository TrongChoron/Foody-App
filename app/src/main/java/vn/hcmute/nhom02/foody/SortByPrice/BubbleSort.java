package vn.hcmute.nhom02.foody.SortByPrice;

import java.util.List;
import static java.util.Collections.swap;
import java.util.Objects;

import vn.hcmute.nhom02.foody.Domain.FoodModel;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Mon, 6/20/2022
 * Time     : 15:35
 * Filename : BubbleSort
 */
public class BubbleSort implements Sort{
    @Override
    public void sortAscending(List<FoodModel> foodModelList) {
        int n = foodModelList.size();
        for (int i = 0; i < n; i++) {
            for(int j=i+1;j<n;j++) {
                if (Objects.requireNonNull((foodModelList.get(i)).getPrice()).compareTo((foodModelList.get(j )).getPrice()) < 0) {
                    swap( foodModelList,i,j);
                }
            }
        }
    }

    @Override
    public void sortDescending(List<FoodModel> foodModelList) {
        int n = foodModelList.size();
        for (int i = 0; i < n; i++) {
            for(int j=i+1;j<n;j++) {
                if (Objects.requireNonNull((foodModelList.get(i)).getPrice()).compareTo((foodModelList.get(j )).getPrice()) > 0) {
                    swap( foodModelList,i,j);
                }
            }
        }
    }

}
