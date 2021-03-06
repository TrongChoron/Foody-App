package vn.hcmute.nhom02.foody.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.database.Database;
import vn.hcmute.nhom02.foody.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static Database database;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView =findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_notifications,R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());


//        database = new Database(this, "foody_test1.sqlite", null, 1);
//        createTableUser();
    }

}
