package vn.hcmute.nhom02.foody.Fragment.manage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

import vn.hcmute.nhom02.foody.Domain.CategoryModel;
import vn.hcmute.nhom02.foody.Domain.Restaurant;
import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.activity.LoginActivity;
import vn.hcmute.nhom02.foody.common.Constants;
import vn.hcmute.nhom02.foody.common.Utils;
import vn.hcmute.nhom02.foody.database.CategoryQuery;
import vn.hcmute.nhom02.foody.database.ICategoryQuery;
import vn.hcmute.nhom02.foody.database.IRestaurantQuery;
import vn.hcmute.nhom02.foody.database.RestaurantQuery;
import vn.hcmute.nhom02.foody.signup.RegisterActivity;

public class ManageRestaurant extends Fragment {

    TextInputEditText etName, etAddress;
    ImageView restaurantImage;
    Button btnSave;
    AutoCompleteTextView categoryName;
//    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;
    View view;
    private ArrayList<CategoryModel> categoryModels;
    private final IRestaurantQuery restaurantQuery = RestaurantQuery.getInstance();
    private final ICategoryQuery categoryQuery = CategoryQuery.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_manage_restaurant, container, false);

        biding();
        categoryModels = (ArrayList<CategoryModel>) categoryQuery.findAllCategory();
        ArrayList<String> categoryNames = new ArrayList<>();
        for(CategoryModel item : categoryModels){
            categoryNames.add(item.getName());
        }

        categoryName = view.findViewById(R.id.autoCompleteTxt);
        adapter = new ArrayAdapter<String>(this.getContext(),R.layout.list_item,categoryNames);
        categoryName.setAdapter(adapter);
        categoryName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item  = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), "item: "+item, Toast.LENGTH_SHORT).show();
            }
        });

        restaurantImage.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, Constants.REQUEST_CODE_FOLDER);
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewRes();
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    private void addNewRes(){
        Integer categoryID=1;
        CategoryModel findByName = categoryQuery.findByName(categoryName.getText().toString());
        final String name = Objects.requireNonNull(etName.getText()).toString();
        final String address = Objects.requireNonNull(etAddress.getText()).toString();
        final byte [] restaurantPic = Utils.convertImageViewToBytes(restaurantImage);
        if(findByName != null){
            categoryID = findByName.getId();
        }

        if (name.isEmpty()) {
            etName.setError(getString(R.string.enter_name));
            etName.requestFocus();
        } else if (address.isEmpty()) {
            etAddress.setError(getString(R.string.enter_address));
            etAddress.requestFocus();
        } else if (findByName == null) {
            categoryName.setError(getString(R.string.select_category));
            categoryName.requestFocus();
        }else {
            try {
                Restaurant restaurant = new Restaurant(null, name, address, restaurantPic, categoryID);
                restaurantQuery.insert(restaurant);
                Toast.makeText(this.getContext(), getString(R.string.insert_restaurant_success), Toast.LENGTH_SHORT).show();
                etName.setText("");
                etAddress.setText("");
                Glide.with(this)
                        .load(R.drawable.photoresbackground)
                        .into(restaurantImage);
                categoryName.setText("");
                etName.requestFocus();
            } catch (Exception ex) {
                Toast.makeText(this.getContext(), getString(R.string.insert_failed, ex.getMessage()), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void biding(){
        etName = view.findViewById(R.id.etName);
        etAddress = view.findViewById(R.id.etAddress);
        restaurantImage = view.findViewById(R.id.imgRestaurant);
        categoryName = view.findViewById(R.id.autoCompleteTxt);
        btnSave = view.findViewById(R.id.btnSaveRestaurant);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.REQUEST_CODE_FOLDER && resultCode == getActivity().RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
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