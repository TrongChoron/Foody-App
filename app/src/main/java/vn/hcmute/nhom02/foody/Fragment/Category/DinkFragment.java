package vn.hcmute.nhom02.foody.Fragment.Category;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.hcmute.nhom02.foody.adapter.DrinkAdapter;
import vn.hcmute.nhom02.foody.Domain.Drink;
import vn.hcmute.nhom02.foody.R;


public class DinkFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    View myFragment ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = LayoutInflater.from(getContext()).inflate(R.layout.fragment_dink, container, false);

        recyclerViewCategory();
        return myFragment;
    }

    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext()
                ,LinearLayoutManager.VERTICAL, false);
        recyclerViewCategoryList = myFragment.findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<Drink> categories = new ArrayList<>();
        categories.add(new Drink("Trà sữa","nuoc","35,000.00đ"));
        categories.add(new Drink("Cofee","caphe","20,000.00đ"));
        categories.add(new Drink("Trà sữa Boo","trasuaboo","35,000.00đ"));

        adapter = new DrinkAdapter(categories);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}