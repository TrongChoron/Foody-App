package vn.hcmute.nhom02.foody.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import vn.hcmute.nhom02.foody.Domain.FoodModel;
import vn.hcmute.nhom02.foody.Fragment.Category.OrderFragment;
import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.activity.AdminRestaurantDetails;
import vn.hcmute.nhom02.foody.activity.ManageActivity;
import vn.hcmute.nhom02.foody.activity.RestaurantDetails;
import vn.hcmute.nhom02.foody.activity.UpdateFood;
import vn.hcmute.nhom02.foody.activity.UpdateRestaurant;
import vn.hcmute.nhom02.foody.common.Common;
import vn.hcmute.nhom02.foody.database.FoodQuery;
import vn.hcmute.nhom02.foody.database.IFoodQuery;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Sun, 5/15/2022
 * Time     : 19:13
 * Filename : CategoryAdapter
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context context;
    private final IFoodQuery foodQuery = FoodQuery.getInstance();
    private ArrayList<FoodModel> foodModels;

    public CategoryAdapter(Context context, ArrayList<FoodModel> foodModels) {
        this.foodModels = foodModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_after_login, parent, false);
        if (Common.currentUser.getEmail().equals("admin@gmail.com")) {
            inflate = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.category_admin, parent, false);
        }
        return new CategoryAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.categoryName.setText(foodModels.get(position).getFoodName());
        holder.categoryDes.setText(foodModels.get(position).getFoodDescription());
        holder.price.setText(foodModels.get(position).getPrice() + "$");
        holder.categoryPic.setImageBitmap(BitmapFactory.
                decodeByteArray(foodModels.get(position).getPhotoFood(), 0, foodModels.get(position).getPhotoFood().length));

        if (Common.currentUser.getEmail().equals("admin@gmail.com")) {
            holder.editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UpdateFood.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("food", foodModels.get(position));
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

            holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        foodQuery.deleteFood(foodModels.get(position));
                        foodModels.remove(position);
                        notifyItemRemoved(position);
                        Toast.makeText(context, "Delete Success", Toast.LENGTH_SHORT).show();
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }

                }
            });

        } else {
            holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OrderFragment.insertOrUpdateOrder(context, foodModels.get(position));
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return foodModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryName, categoryDes, price;
        ImageView categoryPic, addToCartBtn, editBtn, deleteBtn;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryDes = itemView.findViewById(R.id.categoryDes);
            price = itemView.findViewById(R.id.price);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            addToCartBtn = itemView.findViewById(R.id.addToCartBtn);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            editBtn = itemView.findViewById(R.id.editBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }
}
