package vn.hcmute.nhom02.foody.Fragment.Category;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.hcmute.nhom02.foody.Domain.FoodModel;
import vn.hcmute.nhom02.foody.Domain.OrderModel;
import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.activity.PaymentActivity;
import vn.hcmute.nhom02.foody.adapter.OrderAdapter;
import vn.hcmute.nhom02.foody.common.Common;
import vn.hcmute.nhom02.foody.database.IOrderQuery;
import vn.hcmute.nhom02.foody.database.OrderQuery;

public class OrderFragment extends Fragment {
    private static OrderAdapter adapter;
    private static List<OrderModel> orderModelList = new ArrayList<>();
    private static IOrderQuery orderQuery = OrderQuery.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        Button paymentBtn = view.findViewById(R.id.paymentBtn);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        orderModelList = orderQuery.findOrderByUserId(Common.currentUser.getId());
        adapter = new OrderAdapter(getActivity(), orderModelList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void dataFood() {
        if (Common.currentUser != null) {
            List<OrderModel> orders = orderQuery.findOrderByUserId(Common.currentUser.getId());
            if (orders != null)
                orderModelList.addAll(orders);
        }
    }

    public static void insertOrUpdateOrder(Context context, FoodModel foodModel) {
        try {
            if (orderModelList != null)
                orderModelList.clear();
            final OrderModel orderModel = orderQuery.findByFoodAndUserId(foodModel.getId(), Common.currentUser.getId());
            if (orderModel == null) {
                OrderModel orderAdd = new OrderModel(null, 1, 0, foodModel.getId(), Common.currentUser.getId());
                final Long insertOrder = orderQuery.insert(orderAdd);
                if (insertOrder != null) {
                    Toast.makeText(context, context.getString(R.string.add_to_cart_successfully), Toast.LENGTH_SHORT).show();
                    List<OrderModel> orderByUser = orderQuery.findOrderByUserId(Common.currentUser.getId());
                    for(OrderModel item : orderByUser){
                        if(item.getStatus()==0){
                            orderModelList.add(item);
                        }
                    }
//                    orderModelList.addAll(orderQuery.findOrderByUserId(Common.currentUser.getId()));
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(context, context.getString(R.string.add_to_cart_failed), Toast.LENGTH_SHORT).show();
                }
            } else {
                orderModel.setQuantity(orderModel.getQuantity() + 1);
                final Integer updateCountOrder = orderQuery.updateQuantity(orderModel);
                if (updateCountOrder != null) {
                    Toast.makeText(context, context.getString(R.string.update_to_cart_successfully), Toast.LENGTH_SHORT).show();
                    List<OrderModel> orderByUser = orderQuery.findOrderByUserId(Common.currentUser.getId());
                    for(OrderModel item : orderByUser){
                        if(item.getStatus()==0){
                            orderModelList.add(item);
                        }
                    }
//                    orderModelList.addAll(orderQuery.findOrderByUserId(Common.currentUser.getId()));
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(context, context.getString(R.string.update_to_cart_failed), Toast.LENGTH_SHORT).show();
                }
            }


        } catch (Exception ex) {
            Toast.makeText(context, context.getString(R.string.server_error, ex.getMessage()), Toast.LENGTH_SHORT).show();
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    public static void clickBtnPlusCount(Context context, OrderModel orderModel) {
        try {
            orderModel.setQuantity(orderModel.getQuantity() + 1);
            final Integer updateOrder = orderQuery.updateQuantity(orderModel);
            if (updateOrder != null) {
                Toast.makeText(context, context.getString(R.string.update_to_cart_successfully), Toast.LENGTH_SHORT).show();
                orderModelList.clear();
                orderModelList.addAll(orderQuery.findOrderByUserId(Common.currentUser.getId()));
                adapter.notifyDataSetChanged();
            }

        } catch (Exception ex) {
            Toast.makeText(context, context.getString(R.string.server_error, ex.getMessage()), Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public static void clickBtnMinusCount(Context context, OrderModel orderModel) {
        try {
            if (orderModel.getQuantity() > 1) {
                orderModel.setQuantity(orderModel.getQuantity() - 1);
                final Integer updateOrder = orderQuery.updateQuantity(orderModel);
                if (updateOrder != null) {
                    Toast.makeText(context, context.getString(R.string.update_to_cart_successfully), Toast.LENGTH_SHORT).show();
                    orderModelList.clear();
                    orderModelList.addAll(orderQuery.findOrderByUserId(Common.currentUser.getId()));
                    adapter.notifyDataSetChanged();
                }
            } else {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle(R.string.notification);
                alertDialog.setMessage(R.string.remove_order);
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final Integer deleteOrder = orderQuery.deleteOrder(orderModel.getId());
                        if (deleteOrder != null) {
                            orderModelList.clear();
                            Toast.makeText(context, context.getString(R.string.remove_food_success), Toast.LENGTH_SHORT).show();
                            orderModelList.addAll(orderQuery.findOrderByUserId(Common.currentUser.getId()));
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, context.getString(R.string.remove_food_failed), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.show();
            }
        } catch (Exception ex) {
            Toast.makeText(context, context.getString(R.string.server_error, ex.getMessage()), Toast.LENGTH_SHORT).show();
        }
    }
}