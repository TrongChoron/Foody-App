package vn.hcmute.nhom02.foody.adapter;

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

import vn.hcmute.nhom02.foody.Domain.Foods;
import vn.hcmute.nhom02.foody.R;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    ArrayList<Foods> foods;

    public FoodAdapter(ArrayList<Foods> foods) {
        this.foods = foods;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_tag, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        holder.categoryName.setText(foods.get(position).getName());
        holder.price.setText(foods.get(position).getPrice());
        String picUrl = "";
        switch (position) {
            case 0: {
                picUrl = "banhcanh";
                holder.categoryPic.setBackground(ContextCompat
                        .getDrawable(holder.itemView.getContext(), R.drawable.banhcanh));
                break;
            }
            case 1: {
                picUrl = "com";
                holder.categoryPic.setBackground(ContextCompat
                        .getDrawable(holder.itemView.getContext(), R.drawable.com));
                break;
            }
            case 2: {
                picUrl = "bunbo";
                holder.categoryPic.setBackground(ContextCompat
                        .getDrawable(holder.itemView.getContext(), R.drawable.bunbo));
                break;
            }
            case 3: {
                picUrl = "bunmam";
                holder.categoryPic.setBackground(ContextCompat
                        .getDrawable(holder.itemView.getContext(), R.drawable.bunmam));
                break;
            }
        }
        int drawableResourceId = holder.itemView
                .getContext()
                .getResources()
                .getIdentifier(picUrl, "drawable"
                        , holder.itemView
                                .getContext()
                                .getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoryPic);
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryName;
        TextView price;
        ImageView categoryPic;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            price = itemView.findViewById(R.id.price);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
