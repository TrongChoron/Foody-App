package vn.hcmute.nhom02.foody.adapter;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import vn.hcmute.nhom02.foody.Domain.CategoryModel;
import vn.hcmute.nhom02.foody.Domain.Food;
import vn.hcmute.nhom02.foody.Domain.Restaurant;
import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.common.Common;
import vn.hcmute.nhom02.foody.database.CategoryQuery;
import vn.hcmute.nhom02.foody.database.ICategoryQuery;
import vn.hcmute.nhom02.foody.database.IRestaurantQuery;
import vn.hcmute.nhom02.foody.database.RestaurantQuery;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    ArrayList<Restaurant> restaurants;


    public FoodAdapter(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_tag, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        holder.restaurantName.setText(restaurants.get(position).getName());
        holder.address.setText(restaurants.get(position).getAddress());
        byte[] avatar = restaurants.get(position).getRestaurantImage();
        holder.restaurantPic.setImageBitmap(BitmapFactory.decodeByteArray(avatar, 0, avatar.length));
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView restaurantName, address;
        ImageView restaurantPic;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            address = itemView.findViewById(R.id.addressTV);
            restaurantPic = itemView.findViewById(R.id.restaurantPic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
