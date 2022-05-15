package vn.hcmute.nhom02.foody.Fragment.Category;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.hcmute.nhom02.foody.Domain.CategoryModel;
import vn.hcmute.nhom02.foody.Domain.Restaurant;
import vn.hcmute.nhom02.foody.adapter.FoodAdapter;
import vn.hcmute.nhom02.foody.Domain.Food;
import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.database.CategoryQuery;
import vn.hcmute.nhom02.foody.database.ICategoryQuery;
import vn.hcmute.nhom02.foody.database.IRestaurantQuery;
import vn.hcmute.nhom02.foody.database.RestaurantQuery;


public class FoodFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewRestaurantList;
    View myFragment;
    private  ArrayList<Restaurant> restaurants;
    private final IRestaurantQuery restaurantQuery = RestaurantQuery.getInstance();
    private final ICategoryQuery categoryQuery = CategoryQuery.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_food,container,false);
        CategoryModel categoryModel = categoryQuery.findByCode("DA");
        restaurants = (ArrayList<Restaurant>) restaurantQuery.findByCategory(categoryModel.getId());
        recyclerViewCategory();


        return myFragment;
    }

    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext()
                ,LinearLayoutManager.VERTICAL, false);
        recyclerViewRestaurantList = myFragment.findViewById(R.id.recyclerView);
        recyclerViewRestaurantList.setLayoutManager(linearLayoutManager);
        adapter = new FoodAdapter(this.getContext(),restaurants);
        recyclerViewRestaurantList.setAdapter(adapter);
    }
}