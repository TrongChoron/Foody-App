package vn.hcmute.nhom02.foody.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

import vn.hcmute.nhom02.foody.Domain.FoodModel;
import vn.hcmute.nhom02.foody.Domain.Restaurant;
import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.common.Constants;
import vn.hcmute.nhom02.foody.common.Utils;
import vn.hcmute.nhom02.foody.database.FoodQuery;
import vn.hcmute.nhom02.foody.database.IFoodQuery;
import vn.hcmute.nhom02.foody.database.IRestaurantQuery;
import vn.hcmute.nhom02.foody.database.RestaurantQuery;

public class ManageFoodInRestaurant extends AppCompatActivity {
    private TextInputEditText etName, etDes, etPrice;
    private ImageView foodImage;
    private Button btnSave;
    private TextView restaurantName;
    private final IFoodQuery foodQuery = FoodQuery.getInstance();
    private final IRestaurantQuery restaurantQuery = RestaurantQuery.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_food_in_restaurant);
        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return;
        }
        Restaurant restaurant = (Restaurant) bundle.get("restaurant");
        biding();
        restaurantName.setText(restaurant.getName());

        foodImage.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, Constants.REQUEST_CODE_FOLDER);
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewFood();
                goToDetailsRes(restaurant);
            }
        });

    }

    private void goToDetailsRes(Restaurant restaurant){
        Intent intent = new Intent(this, AdminRestaurantDetails.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("restaurant",restaurant);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void createNewFood(){
        Integer resID=1;
        Restaurant findByName = restaurantQuery.findByName(restaurantName.getText().toString());
        final String name = Objects.requireNonNull(etName.getText()).toString();
        final String description = Objects.requireNonNull(etDes.getText()).toString();
        final Float price = Float.valueOf(Objects.requireNonNull(etPrice.getText()).toString());
        final byte [] foodPic = Utils.convertImageViewToBytes(foodImage);
        if(findByName != null){
            resID = findByName.getId();
        }

        if (name.isEmpty()) {
            etName.setError(getString(R.string.enter_name));
            etName.requestFocus();
        } else if (description.isEmpty()) {
            etDes.setError(getString(R.string.enter_des));
            etDes.requestFocus();
        }
        else if(price == 0){
            etPrice.setError(getString(R.string.enter_price));
            etPrice.requestFocus();
        }
        else {
            try {
                FoodModel foodModel = new FoodModel(null, name, description,price, foodPic, resID);
                foodQuery.insert(foodModel);
                Toast.makeText(this, getString(R.string.insert_restaurant_success), Toast.LENGTH_SHORT).show();
//                etName.setText("");
//                etDes.setText("");
//                etPrice.setText("");
//                Glide.with(this)
//                        .load(R.drawable.photoresbackground)
//                        .into(foodImage);
//                etName.requestFocus();
            } catch (Exception ex) {
                Toast.makeText(this, getString(R.string.insert_failed, ex.getMessage()), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void biding(){
        etName = findViewById(R.id.etName);
        etDes = findViewById(R.id.etDes);
        etPrice = findViewById(R.id.etPrice);
        foodImage = findViewById(R.id.imgFood);
        btnSave = findViewById(R.id.btnSaveFood);
        restaurantName = findViewById(R.id.restaurantName);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.REQUEST_CODE_FOLDER && resultCode == this.RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = this.getContentResolver().openInputStream(uri);
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(inputStream),
                        foodImage.getWidth(), foodImage.getHeight(), true);
                foodImage.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}