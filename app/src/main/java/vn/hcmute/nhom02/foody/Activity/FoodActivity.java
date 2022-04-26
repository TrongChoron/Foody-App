package vn.hcmute.nhom02.foody.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

import vn.hcmute.nhom02.foody.Adapter.CategoryAdaptor;
import vn.hcmute.nhom02.foody.Domain.CategoryDomain;
import vn.hcmute.nhom02.foody.R;

public class FoodActivity extends AppCompatActivity {

    private LinearLayout drinkBtn;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        drinkBtn = findViewById(R.id.drinkOption);
        drinkBtn.setOnClickListener(view -> startActivity(new Intent(FoodActivity.this, DrinkActivity.class)));

        recyclerViewCategory();
    }
    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this
                ,LinearLayoutManager.VERTICAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categories = new ArrayList<>();
        categories.add(new CategoryDomain("Bánh Canh","banhcanh","35,000.00đ"));
        categories.add(new CategoryDomain("Cơm","com","25,000.00đ"));
        categories.add(new CategoryDomain("Bún Bò","bunbo","40,000.00đ"));
        categories.add(new CategoryDomain("Bún mắm","bunmam","35,000.00đ"));

        adapter = new CategoryAdaptor(categories);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}