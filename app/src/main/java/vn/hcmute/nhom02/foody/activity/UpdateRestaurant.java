package vn.hcmute.nhom02.foody.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.InputStream;
import java.util.Objects;

import vn.hcmute.nhom02.foody.Domain.CategoryModel;
import vn.hcmute.nhom02.foody.Domain.Restaurant;
import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.common.Common;
import vn.hcmute.nhom02.foody.common.Constants;
import vn.hcmute.nhom02.foody.common.Utils;
import vn.hcmute.nhom02.foody.database.CategoryQuery;
import vn.hcmute.nhom02.foody.database.FoodQuery;
import vn.hcmute.nhom02.foody.database.ICategoryQuery;
import vn.hcmute.nhom02.foody.database.IFoodQuery;
import vn.hcmute.nhom02.foody.database.IRestaurantQuery;
import vn.hcmute.nhom02.foody.database.RestaurantQuery;

public class UpdateRestaurant extends AppCompatActivity {
    TextInputEditText etName, etAddress;
    ImageView restaurantImage;
    Button btnSave;
    AutoCompleteTextView categoryName;
    final private ICategoryQuery categoryQuery = CategoryQuery.getInstance();
    final private IRestaurantQuery restaurantQuery = RestaurantQuery.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_restaurant);
        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return;
        }
        Restaurant restaurant = (Restaurant) bundle.get("restaurant");
        biding();
        setInfoRes(restaurant);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRes(restaurant);
            }
        });

        restaurantImage.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, Constants.REQUEST_CODE_FOLDER);
        });

    }

    private void updateRes(Restaurant restaurant) {
        try {
                final String name = Objects.requireNonNull(etName.getText()).toString();
                final String address = Objects.requireNonNull(etAddress.getText()).toString();
                final byte[] pic = Utils.convertImageViewToBytes(restaurantImage);
                CategoryModel categoryModel = categoryQuery.findByName(categoryName.getText().toString());

                restaurant.setName(name);
                restaurant.setAddress(address);
                restaurant.setRestaurantImage(pic);
                restaurant.setCategoryId(categoryModel.getId());
                Integer updateRes = restaurantQuery.updateRestaurant(restaurant);
            if (updateRes > 0) {
                Toast.makeText(this, R.string.update_profile_successfully, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,ManageActivity.class));
            } else {
                Toast.makeText(this, R.string.update_profile_failed, Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            ex.printStackTrace();
            Toast.makeText(this, getString(R.string.server_error, ex.getMessage()), Toast.LENGTH_SHORT).show();
        }

    }

    private void biding(){
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        restaurantImage = findViewById(R.id.imgRestaurant);
        categoryName = findViewById(R.id.autoCompleteTxt);
        btnSave = findViewById(R.id.btnSaveRestaurant);
    }
    private void setInfoRes( Restaurant restaurant){
        etName.setText(restaurant.getName());
        etAddress.setText(restaurant.getAddress());
        restaurantImage.setImageBitmap((BitmapFactory.decodeByteArray(restaurant.getRestaurantImage(), 0, restaurant.getRestaurantImage().length)));
        CategoryModel categoryModel = categoryQuery.findById(restaurant.getCategoryId());
        categoryName.setText(categoryModel.getName());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.REQUEST_CODE_FOLDER && resultCode == this.RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = this.getContentResolver().openInputStream(uri);
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(inputStream),
                        restaurantImage.getWidth(), restaurantImage.getHeight(), true);
                restaurantImage.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}