package vn.hcmute.nhom02.foody.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.hcmute.nhom02.foody.Domain.CategoryModel;
import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.common.Utils;
import vn.hcmute.nhom02.foody.database.CategoryQuery;
import vn.hcmute.nhom02.foody.database.Database;
import vn.hcmute.nhom02.foody.database.ICategoryQuery;
import vn.hcmute.nhom02.foody.database.IUserQuery;
import vn.hcmute.nhom02.foody.database.UserQuery;
import vn.hcmute.nhom02.foody.signup.User;

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
        createTableUser();
        createTableCategory();
        createTableRestaurant();
        createTableFood();
        createTableOrder();
        insertNecessary();

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

    private void createTableUser() {
        database.QueryData("create table if not exists user (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name varchar(255)," +
                "email varchar(255) unique," +
                "password varchar(20)," +
                "phone varchar(10) ," +
                "address varchar(255)," +
                "avatar blob)");
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
    private void createTableFood(){
        database.QueryData("CREATE TABLE IF NOT EXISTS food(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name varchar(255), " +
                "description varchar(255)," +
                "price float,"+
                "pic blob,"+
                "restaurantID INTEGER NOT NULL," +
                " FOREIGN KEY (restaurantID) REFERENCES restaurant(id))");
    }

    private void createTableOrder(){
        database.QueryData("CREATE TABLE IF NOT EXISTS orders(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "quantity INTEGER, " +
                "status INTEGER CHECK (status IN (0, 1)) ,"+
                "food_id INTEGER, " +
                "user_id INTEGER)");
    }

    private void insertNecessary(){
        final ICategoryQuery categoryQuery = CategoryQuery.getInstance();
        final IUserQuery userQuery = UserQuery.getInstance();
        List<CategoryModel> categoryModels = categoryQuery.findAllCategory();
        if(categoryModels.size() == 0){
            CategoryModel categoryModelDrink = new CategoryModel(null, "Food", "DA");
            Long insert1 = categoryQuery.insert(categoryModelDrink);
            CategoryModel categoryModelFood = new CategoryModel(null, "Drink", "DU");
            Long insert2 = categoryQuery.insert(categoryModelFood);
        }
        List<User> users = userQuery.getAllUser();
        if(users.size()==0){
            try{
                User user = new User(null,"Admin","admin@gmail.com","admin","","",null);
                userQuery.insert(user);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }

        }
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