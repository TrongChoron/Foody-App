package vn.hcmute.nhom02.foody.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.database.Database;

public class SplashActivity extends AppCompatActivity {
    private ImageView ivSplash;
    private TextView tvSplash;
    private Animation animation;
    public static Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash);
        database = new Database(this);
        bindingView();
        createTableCategory();
        createTableRestaurant();
//            dropCategoryTable();

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void createTableCategory() {
        database.QueryData("create table if not exists category(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name varchar(255), " +
                "code varchar(255)" +
                ")");
    }

    private void createTableRestaurant(){
        database.QueryData("CREATE TABLE IF NOT EXISTS restaurant(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name varchar(255), " +
                "address varchar(255)," +
                "pic blob,"+
                "categoryID INTEGER NOT NULL," +
                " FOREIGN KEY (categoryID) REFERENCES category(id))");
    }
    private void dropCategoryTable(){
        database.QueryData("Drop table category");
    }

    private void bindingView() {
        ivSplash = findViewById(R.id.ivSplash);
        tvSplash = findViewById(R.id.tvSplash);
        animation = AnimationUtils.loadAnimation(this, R.anim.splah_animation);
    }
    @Override
    protected void onStart() {
        super.onStart();
        ivSplash.startAnimation(animation);
        tvSplash.startAnimation(animation);
    }
}