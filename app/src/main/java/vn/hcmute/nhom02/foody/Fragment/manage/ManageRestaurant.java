package vn.hcmute.nhom02.foody.Fragment.manage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import vn.hcmute.nhom02.foody.R;

public class ManageRestaurant extends Fragment {

    String[] items = {"test","test1","test2"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_manage_restaurant, container, false);


        autoCompleteTextView = view.findViewById(R.id.autoCompleteTxt);
        adapter = new ArrayAdapter<String>(this.getContext(),R.layout.list_item,items);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item  = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), "item: "+item, Toast.LENGTH_SHORT).show();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}