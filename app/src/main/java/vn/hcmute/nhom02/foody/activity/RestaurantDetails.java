package vn.hcmute.nhom02.foody.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.hcmute.nhom02.foody.Domain.FoodModel;
import vn.hcmute.nhom02.foody.Domain.Restaurant;
import vn.hcmute.nhom02.foody.R;
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
    private ImageView resPic;
    ArrayList<FoodModel> foodModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
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
    }
}