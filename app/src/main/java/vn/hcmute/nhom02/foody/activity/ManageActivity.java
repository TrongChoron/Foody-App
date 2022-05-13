package vn.hcmute.nhom02.foody.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.databinding.ActivityMainBinding;
import vn.hcmute.nhom02.foody.databinding.ActivityManageBinding;

public class ManageActivity extends AppCompatActivity {
    private ActivityManageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView =findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                 R.id.navigation_dashboard,R.id.navigation_restaurant,R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_admin);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
}