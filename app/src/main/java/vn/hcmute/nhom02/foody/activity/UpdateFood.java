package vn.hcmute.nhom02.foody.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.InputStream;
import java.util.Objects;

import vn.hcmute.nhom02.foody.Domain.FoodModel;
import vn.hcmute.nhom02.foody.Domain.Restaurant;
import vn.hcmute.nhom02.foody.Fragment.manage.ManageRestaurant;
import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.common.Constants;
import vn.hcmute.nhom02.foody.common.Utils;
import vn.hcmute.nhom02.foody.database.FoodQuery;
import vn.hcmute.nhom02.foody.database.IFoodQuery;
import vn.hcmute.nhom02.foody.database.IRestaurantQuery;
import vn.hcmute.nhom02.foody.database.RestaurantQuery;

public class UpdateFood extends AppCompatActivity {
    private TextInputEditText etName, etDes, etPrice;
    private ImageView foodImage;
    private Button btnSave;
    private TextView restaurantName;
    private final IFoodQuery foodQuery  = FoodQuery.getInstance();
    private final IRestaurantQuery restaurantQuery= RestaurantQuery.getInstance();
    private  FoodModel foodModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_food);
        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return;
        }
        foodModel = (FoodModel) bundle.get("food");
        biding();
        getFoodInfo();

        foodImage.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, Constants.REQUEST_CODE_FOLDER);
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateFood(foodModel);
                goToHome();
            }
        });

    }

    private void goToHome() {
        Intent intent = new Intent(this, ManageActivity.class);
        startActivity(intent);
    }

    private void updateFood(FoodModel foodModel) {
        try {
            final String name = Objects.requireNonNull(etName.getText()).toString();
            final String description = Objects.requireNonNull(etDes.getText()).toString();
            final Float price = Float.valueOf(Objects.requireNonNull(etPrice.getText()).toString());
            final byte[] foodPic = Utils.convertImageViewToBytes(foodImage);
            foodModel.setFoodName(name);
            foodModel.setFoodDescription(description);
            foodModel.setPrice(price);
            foodModel.setPhotoFood(foodPic);
            Integer updateFood = foodQuery.update(foodModel);
            if (updateFood > 0) {
                Toast.makeText(this, getString(R.string.update_profile_successfully), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.update_profile_failed, Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            ex.printStackTrace();
            Toast.makeText(this, getString(R.string.server_error, ex.getMessage()), Toast.LENGTH_SHORT).show();
        }


    }

    private void getFoodInfo(){
        etName.setText(foodModel.getFoodName());
        etDes.setText(foodModel.getFoodDescription());
        etPrice.setText(foodModel.getPrice().toString());
        foodImage.setImageBitmap(BitmapFactory.decodeByteArray(foodModel.getPhotoFood(), 0, foodModel.getPhotoFood().length));
        Restaurant restaurant = restaurantQuery.findById(foodModel.getRestaurantID());
        restaurantName.setText(restaurant.getName());
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