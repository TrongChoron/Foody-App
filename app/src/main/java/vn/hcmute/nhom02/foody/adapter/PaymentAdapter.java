package vn.hcmute.nhom02.foody.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.hcmute.nhom02.foody.Domain.FoodModel;
import vn.hcmute.nhom02.foody.Domain.OrderModel;
import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.database.FoodQuery;
import vn.hcmute.nhom02.foody.database.IFoodQuery;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Thu, 5/19/2022
 * Time     : 08:24
 * Filename : PaymentAdapter
 */
public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {
    private Context context;
    private List<OrderModel> orderModels;
    private final IFoodQuery foodQuery = FoodQuery.getInstance();

    public PaymentAdapter(Context context, List<OrderModel> orderModels) {
        this.context = context;
        this.orderModels = orderModels;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.payment_tag, parent, false);
        return new PaymentViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        final OrderModel orderModel = orderModels.get(position);
        final FoodModel foodModel = foodQuery.findById(orderModel.getFoodID());


        Glide.with(context)
                .load(foodModel.getPhotoFood())
                .into(holder.foodPic);
        holder.foodName.setText(foodModel.getFoodName());
        holder.foodPrice.setText(foodModel.getPrice()+ "$");
        holder.quantity.setText(orderModel.getQuantity().toString());
        holder.total.setText(foodModel.getPrice()*orderModel.getQuantity()+"$");
    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }

    public static class PaymentViewHolder extends RecyclerView.ViewHolder {
        ImageView foodPic;
        TextView foodName, foodPrice, quantity, total;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            foodPic = itemView.findViewById(R.id.foodPic);
            foodName = itemView.findViewById(R.id.foodName);
            foodPrice = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.quantity);
            total = itemView.findViewById(R.id.total);
        }
    }
}
