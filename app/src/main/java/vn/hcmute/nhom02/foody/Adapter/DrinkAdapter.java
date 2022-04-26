package vn.hcmute.nhom02.foody.Adapter;

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

import vn.hcmute.nhom02.foody.Domain.Drinks;
import vn.hcmute.nhom02.foody.R;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder>{
    ArrayList<Drinks> drinks;

    public DrinkAdapter(ArrayList<Drinks> drinks) {
        this.drinks = drinks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_tag, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryName.setText(drinks.get(position).getName());
        holder.price.setText(drinks.get(position).getPrice());
        String picUrl = "";
        switch (position) {
            case 0: {
                picUrl = "nuoc";
                holder.categoryPic.setBackground(ContextCompat
                        .getDrawable(holder.itemView.getContext(), R.drawable.nuoc));
                break;
            }
            case 1: {
                picUrl = "caphe";
                holder.categoryPic.setBackground(ContextCompat
                        .getDrawable(holder.itemView.getContext(), R.drawable.caphe));
                break;
            }
            case 2: {
                picUrl = "trasuaboo";
                holder.categoryPic.setBackground(ContextCompat
                        .getDrawable(holder.itemView.getContext(), R.drawable.trasuaboo));
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
        return drinks.size();
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
