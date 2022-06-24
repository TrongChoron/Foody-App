package vn.hcmute.nhom02.foody.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.hcmute.nhom02.foody.Domain.FoodModel;
import vn.hcmute.nhom02.foody.Domain.OrderModel;
import vn.hcmute.nhom02.foody.Domain.OrderRequest;
import vn.hcmute.nhom02.foody.Domain.ReceiveOrderRequest;
import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.adapter.OrderAdapter;
import vn.hcmute.nhom02.foody.adapter.PaymentAdapter;
import vn.hcmute.nhom02.foody.common.Common;
import vn.hcmute.nhom02.foody.database.FoodQuery;
import vn.hcmute.nhom02.foody.database.IFoodQuery;
import vn.hcmute.nhom02.foody.database.IOrderQuery;
import vn.hcmute.nhom02.foody.database.OrderQuery;

public class PaymentActivity extends AppCompatActivity {
    private RecyclerView rcvPayment;
    private TextView totalTV;
    private Button btnBuy, btnHome;
    private PaymentAdapter adapter;
    private List<OrderModel> orders;
    private final IOrderQuery orderQuery = OrderQuery.getInstance();
    private final IFoodQuery foodQuery = FoodQuery.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        biding();
        orders = new ArrayList<>();
        adapter = new PaymentAdapter(this, orders);

        rcvPayment.setLayoutManager(new LinearLayoutManager(this));
        rcvPayment.setAdapter(adapter);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                if (orders.size() > 0) {
                    clickPayment();
                } else {
                    Toast.makeText(PaymentActivity.this, getString(R.string.payment_failed), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void clickPayment() {
        for(OrderModel item :orders) {
            OrderRequest orderRequest = new ReceiveOrderRequest(new OrderModel());
            orderRequest.sendRequest("I want to order " +item);
        }
        orderQuery.deleteOrderByUser(Common.currentUser.getId());
        orders.clear();
        totalTV.setText("$");
        Toast.makeText(PaymentActivity.this, getString(R.string.payment_success), Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
    }

    private void biding() {
        rcvPayment = findViewById(R.id.rcvPayment);
        totalTV = findViewById(R.id.tvTotalOrderPrice);
        btnBuy = findViewById(R.id.btnBuy);
        btnHome = findViewById(R.id.homeBtn);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataOrder();
    }

    @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
    private void loadDataOrder() {
        final List<OrderModel> orderByUserId = orderQuery.findOrderByUserId(Common.currentUser.getId());

        orders.addAll(orderByUserId);
        Float total = 0.0f;
        for (OrderModel order : orders) {
            FoodModel foodModel = foodQuery.findById(order.getFoodID());
            total += foodModel.getPrice() * order.getQuantity();
        }
        totalTV.setText("Total Price: " + total + "$");
        adapter.notifyDataSetChanged();

    }
}