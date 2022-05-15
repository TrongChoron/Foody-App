package vn.hcmute.nhom02.foody.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import vn.hcmute.nhom02.foody.Domain.Restaurant;
import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.database.IRestaurantQuery;
import vn.hcmute.nhom02.foody.database.RestaurantQuery;

public class RestaurantDetails extends AppCompatActivity {
    final private IRestaurantQuery restaurantQuery = RestaurantQuery.getInstance();

    private TextView resName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        resName = findViewById(R.id.tvNameRes);
        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return;
        }
        Restaurant restaurant = (Restaurant) bundle.get("restaurant");
        resName.setText(restaurant.getName());
    }
}