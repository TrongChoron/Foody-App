package vn.hcmute.nhom02.foody.Fragment.Category;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.hcmute.nhom02.foody.adapter.FoodAdapter;
import vn.hcmute.nhom02.foody.Domain.Food;
import vn.hcmute.nhom02.foody.R;


public class FoodFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    View myFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = LayoutInflater.from(getContext()).inflate(R.layout.fragment_food,container,false);
        recyclerViewCategory();
        return myFragment;
    }

    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext()
                ,LinearLayoutManager.VERTICAL, false);
        recyclerViewCategoryList = myFragment.findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<Food> categories = new ArrayList<>();
        categories.add(new Food("Bánh Canh","banhcanh","35,000.00đ"));
        categories.add(new Food("Cơm","com","25,000.00đ"));
        categories.add(new Food("Bún Bò","bunbo","40,000.00đ"));
        categories.add(new Food("Bún mắm","bunmam","35,000.00đ"));

        adapter = new FoodAdapter(categories);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}