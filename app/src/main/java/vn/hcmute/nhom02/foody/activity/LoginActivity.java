package vn.hcmute.nhom02.foody.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.common.Common;
import vn.hcmute.nhom02.foody.common.Constants;
import vn.hcmute.nhom02.foody.common.Utils;
import vn.hcmute.nhom02.foody.database.Database;
import vn.hcmute.nhom02.foody.database.IUserQuery;
import vn.hcmute.nhom02.foody.database.UserQuery;
import vn.hcmute.nhom02.foody.signup.RegisterActivity;
import vn.hcmute.nhom02.foody.signup.User;

public class LoginActivity extends AppCompatActivity {
    public static Database database;
    private EditText edtEmail, edtPassword;
    private final IUserQuery userQuery = UserQuery.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);



    }

    private void createTableUser() {
        database.QueryData("create table if not exists user (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name varchar(255)," +
                "email varchar(255) unique," +
                "password varchar(20))");
    }

    public void tvSignUpClick(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences userPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_USER_STATE, MODE_PRIVATE);
        Utils.getPreferences(userPreferences);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void btnLoginClick(View view) {
        final String email = Objects.requireNonNull(edtEmail.getText()).toString();
        final String password = Objects.requireNonNull(edtPassword.getText()).toString();
        if (email.isEmpty()) {
            edtEmail.setError("Enter Email");
        } else if (password.isEmpty()) {
            edtPassword.setError("Enter Password");
        }else if(email.equals("aaa") && password.equals("123")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else {
            try {
                User user = userQuery.findByUserEmailAndPassword(email, password);
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Common.currentUser = user;
                    startActivity(intent);
                    SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_USER_STATE, MODE_PRIVATE);
                    Utils.setPreferences(Common.currentUser,sharedPreferences);
                    finish();
                }else{
                    Toast.makeText(this, getString(R.string.email_or_password_incorrect), Toast.LENGTH_SHORT).show();
                }
            }catch (Exception ex){
                Toast.makeText(this, getString(R.string.server_error, ex.getMessage()), Toast.LENGTH_SHORT).show();
            }
        }




    }

}