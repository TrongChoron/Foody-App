package vn.hcmute.nhom02.foody.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.hcmute.nhom02.foody.Domain.FoodModel;
import vn.hcmute.nhom02.foody.Domain.Restaurant;
import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.SortByPrice.BubbleSort;
import vn.hcmute.nhom02.foody.SortByPrice.Sort;
import vn.hcmute.nhom02.foody.adapter.CategoryAdapter;
import vn.hcmute.nhom02.foody.adapter.FoodAdapter;
import vn.hcmute.nhom02.foody.database.FoodQuery;
import vn.hcmute.nhom02.foody.database.IFoodQuery;
import vn.hcmute.nhom02.foody.database.IRestaurantQuery;
import vn.hcmute.nhom02.foody.database.RestaurantQuery;

public class RestaurantDetails extends AppCompatActivity {
    final private IFoodQuery foodQuery = FoodQuery.getInstance();
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    private TextView restaurantNameTV, restaurantAddressTV;
    private ImageView resPic, homeBtn;
    private Button sortBtnAsc, sortBtnDes;
    private Sort sort;
    ArrayList<FoodModel> foodModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        Restaurant restaurant = (Restaurant) bundle.get("restaurant");
        biding();
        restaurantNameTV.setText(restaurant.getName());
        restaurantAddressTV.setText(restaurant.getAddress());
        resPic.setImageBitmap(BitmapFactory.decodeByteArray(restaurant.getRestaurantImage(), 0, restaurant.getRestaurantImage().length));

        foodModels = (ArrayList<FoodModel>) foodQuery.findFoodByRestaurant(restaurant.getId());
        recyclerViewCategory();

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHomeActivity();
            }
        });

//        sortBtnAsc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sort = new BubbleSort();
//                sort.sortAscending(foodModels);
//                adapter.notifyDataSetChanged();
//            }
//        });
//
//        sortBtnDes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sort = new BubbleSort();
//                sort.sortDescending(foodModels);
//                adapter.notifyDataSetChanged();
//            }
//        });

    }

    private void goToHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this
                , LinearLayoutManager.VERTICAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        adapter = new CategoryAdapter(this, foodModels);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void biding() {
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        restaurantNameTV = findViewById(R.id.restaurantName);
        restaurantAddressTV = findViewById(R.id.addressTV);
        resPic = findViewById(R.id.imgRes);
        homeBtn = findViewById(R.id.homeBtn);
        sortBtnAsc = findViewById(R.id.sortBtnAsc);
        sortBtnDes = findViewById(R.id.sortBtnDes);
    }

    public void sortAsc(View view) {
        sort = new BubbleSort();
        sort.sortAscending(foodModels);
        adapter.notifyDataSetChanged();
    }

    public void sortDes(View view) {
        sort = new BubbleSort();
        sort.sortDescending(foodModels);
        adapter.notifyDataSetChanged();
    }
}