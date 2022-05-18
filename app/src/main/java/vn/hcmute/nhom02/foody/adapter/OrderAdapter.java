package vn.hcmute.nhom02.foody.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.hcmute.nhom02.foody.Domain.FoodModel;
import vn.hcmute.nhom02.foody.Domain.OrderModel;
import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.common.Utils;
import vn.hcmute.nhom02.foody.database.FoodQuery;
import vn.hcmute.nhom02.foody.database.IFoodQuery;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Tue, 4/26/2022
 * Time     : 4:43 PM
 * Filename : OrderAdapter
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Context context;
    private List<OrderModel> orderModels;
    private List<OrderModel> orderModelsSold;
    private final IFoodQuery foodQuery = FoodQuery.getInstance();

    public OrderAdapter(Context context, List<OrderModel> orderModels) {
        this.context = context;
        this.orderModels = orderModels;
        this.orderModelsSold = orderModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_order, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderModel orderModel = orderModels.get(position);
        FoodModel foodModel = foodQuery.findById(orderModel.getFoodID());

        Glide.with(context)
                .load(Utils.convertBytesToBitMap(foodModel.getPhotoFood()))
                .into(holder.foodImage);
        holder.foodName.setText(foodModel.getFoodName());
        holder.price.setText(foodModel.getPrice()+ "$");
        holder.quantity.setText(orderModel.getQuantity().toString());
        holder.totalPrice.setText(orderModel.getQuantity() * foodModel.getPrice() + "$");
    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView foodName,price,totalPrice,quantity;
        ImageView foodImage,btnAdd,btnMinus;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            price = itemView.findViewById(R.id.price);
            totalPrice = itemView.findViewById(R.id.totalPrice);
            foodImage = itemView.findViewById(R.id.foodImage);
            quantity = itemView.findViewById(R.id.quantity);
            btnAdd = itemView.findViewById(R.id.addBtn);
            btnMinus = itemView.findViewById(R.id.minusBtn);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
