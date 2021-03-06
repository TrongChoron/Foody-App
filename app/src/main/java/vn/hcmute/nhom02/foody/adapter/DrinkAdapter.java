package vn.hcmute.nhom02.foody.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.hcmute.nhom02.foody.Domain.FoodModel;
import vn.hcmute.nhom02.foody.Domain.Restaurant;
import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.activity.AdminRestaurantDetails;
import vn.hcmute.nhom02.foody.activity.RestaurantDetails;
import vn.hcmute.nhom02.foody.activity.UpdateRestaurant;
import vn.hcmute.nhom02.foody.common.Common;
import vn.hcmute.nhom02.foody.database.FoodQuery;
import vn.hcmute.nhom02.foody.database.IFoodQuery;
import vn.hcmute.nhom02.foody.database.IRestaurantQuery;
import vn.hcmute.nhom02.foody.database.RestaurantQuery;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder>{
    ArrayList<Restaurant> restaurants;
    private final Context context;
    private final IRestaurantQuery restaurantQuery = RestaurantQuery.getInstance();
    private final IFoodQuery foodQuery = FoodQuery.getInstance();

    public DrinkAdapter(Context context, ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_tag, parent, false);
        if (Common.currentUser.getEmail().equals("admin@gmail.com")) {
            inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_manage, parent, false);
        }

        return new DrinkAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (!Common.currentUser.getEmail().equals("admin@gmail.com")) {
            holder.restaurantName.setText(restaurants.get(position).getName());
            holder.address.setText(restaurants.get(position).getAddress());
            byte[] avatar = restaurants.get(position).getRestaurantImage();
            holder.restaurantPic.setImageBitmap(BitmapFactory.decodeByteArray(avatar, 0, avatar.length));

            holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickToGoDetail(restaurants.get(position));
                }
            });
        } else {
            holder.restaurantName.setText(restaurants.get(position).getName());
            byte[] avatar = restaurants.get(position).getRestaurantImage();
            holder.restaurantPic.setImageBitmap(BitmapFactory.decodeByteArray(avatar, 0, avatar.length));
            holder.editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UpdateRestaurant.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("restaurant", restaurants.get(position));
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
            holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickToGoAdminDetail(restaurants.get(position));
                }
            });

            holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    List<FoodModel> foods = foodQuery.findFoodByRestaurant(restaurants.get(position).getId());
                    if (foods.size() > 0) {
                        for (FoodModel item : foods) {
                            foodQuery.deleteFood(item);
                        }
                    }
                    restaurantQuery.deleteRestaurant(restaurants.get(position));
                    restaurants.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(context, "Delete Success", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void onClickToGoAdminDetail(Restaurant restaurant) {
        Intent intent = new Intent(context, AdminRestaurantDetails.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("restaurant", restaurant);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private void onClickToGoDetail(Restaurant restaurant) {
        Intent intent = new Intent(context, RestaurantDetails.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("restaurant", restaurant);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (restaurants != null) {
            return restaurants.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView restaurantName, address;
        ImageView restaurantPic, editBtn, deleteBtn;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            address = itemView.findViewById(R.id.addressTV);
            restaurantPic = itemView.findViewById(R.id.restaurantPic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            editBtn = itemView.findViewById(R.id.editBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }
}
