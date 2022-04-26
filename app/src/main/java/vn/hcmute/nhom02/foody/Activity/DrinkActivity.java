package vn.hcmute.nhom02.foody.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import vn.hcmute.nhom02.foody.Adapter.DrinkAdaptor;
import vn.hcmute.nhom02.foody.Domain.Drinks;
import vn.hcmute.nhom02.foody.R;

public class DrinkActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        recyclerViewCategory();
    }
    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this
                ,LinearLayoutManager.VERTICAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<Drinks> categories = new ArrayList<>();
        categories.add(new Drinks("Trà sữa","nuoc","35,000.00đ"));
        categories.add(new Drinks("Cofee","caphe","20,000.00đ"));
        categories.add(new Drinks("Trà sữa Boo","trasuaboo","35,000.00đ"));

        adapter = new DrinkAdaptor(categories);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}