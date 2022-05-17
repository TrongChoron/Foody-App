package vn.hcmute.nhom02.foody.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.hcmute.nhom02.foody.Domain.FoodModel;
import vn.hcmute.nhom02.foody.Domain.Restaurant;
import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.adapter.CategoryAdapter;
import vn.hcmute.nhom02.foody.database.FoodQuery;
import vn.hcmute.nhom02.foody.database.IFoodQuery;

public class AdminRestaurantDetails extends AppCompatActivity {
    final private IFoodQuery foodQuery = FoodQuery.getInstance();
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    private TextView restaurantNameTV, restaurantAddressTV;
    private ImageView resPic,addFoodBtn,homeBtn;
    ArrayList<FoodModel> foodModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_restaurant_details);
        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return;
        }
        Restaurant restaurant = (Restaurant) bundle.get("restaurant");
        biding();
        restaurantNameTV.setText(restaurant.getName());
        restaurantAddressTV.setText(restaurant.getAddress());
        resPic.setImageBitmap(BitmapFactory.decodeByteArray(restaurant.getRestaurantImage(), 0, restaurant.getRestaurantImage().length));

        foodModels = (ArrayList<FoodModel>) foodQuery.findFoodByRestaurant(restaurant.getId());
        recyclerViewCategory();

        addFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddFood(restaurant);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToManageActivity();
            }
        });
    }

    private void goToManageActivity(){
        Intent intent = new Intent(this, ManageActivity.class);
        startActivity(intent);
    }


    private void goToAddFood(Restaurant restaurant){
        Intent intent = new Intent(this, ManageFoodInRestaurant.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("restaurant",restaurant);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this
                ,LinearLayoutManager.VERTICAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        adapter = new CategoryAdapter(this,foodModels);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void biding() {
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        restaurantNameTV = findViewById(R.id.restaurantName);
        restaurantAddressTV = findViewById(R.id.addressTV);
        resPic = findViewById(R.id.imgRes);
        addFoodBtn = findViewById(R.id.addFoodBtn);
        homeBtn = findViewById(R.id.homeBtn);
    }
}