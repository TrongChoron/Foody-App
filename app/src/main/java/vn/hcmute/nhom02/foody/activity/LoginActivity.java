package vn.hcmute.nhom02.foody.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vn.hcmute.nhom02.foody.Domain.CategoryModel;
import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.common.Common;
import vn.hcmute.nhom02.foody.common.Constants;
import vn.hcmute.nhom02.foody.common.Utils;
import vn.hcmute.nhom02.foody.database.CategoryQuery;
import vn.hcmute.nhom02.foody.database.Database;
import vn.hcmute.nhom02.foody.database.ICategoryQuery;
import vn.hcmute.nhom02.foody.database.IUserQuery;
import vn.hcmute.nhom02.foody.database.UserQuery;
import vn.hcmute.nhom02.foody.signup.RegisterActivity;
import vn.hcmute.nhom02.foody.signup.User;

public class LoginActivity extends AppCompatActivity {
    public static Database database;
    private EditText edtEmail, edtPassword;
    private CheckBox cbRemember;
    SharedPreferences sharedPreferences;
    private final IUserQuery userQuery = UserQuery.getInstance();
    private final ICategoryQuery categoryQuery = CategoryQuery.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bidingView();


        sharedPreferences = getSharedPreferences(Constants.DATA_LOGIN, MODE_PRIVATE);
        edtEmail.setText(sharedPreferences.getString("email", ""));
        edtPassword.setText(sharedPreferences.getString("password", ""));
        cbRemember.setChecked(sharedPreferences.getBoolean("checked", false));


//        CategoryModel categoryModel = new CategoryModel(null, "Food", "DA");
//        Long insert = categoryQuery.insert(categoryModel);
//        Integer delete = categoryQuery.delete(3);

        List<User> categoryModels = new ArrayList<>();
        List<User> results = userQuery.getAllUser();
        if(results !=null)
        categoryModels.addAll(results);

        System.out.println(String.valueOf(categoryModels));

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
            edtEmail.requestFocus();
        } else if (!isValidEmail(email)) {
            edtEmail.setError(getString(R.string.email_invalidate));
            edtEmail.requestFocus();
        } else if (password.isEmpty()) {
            edtPassword.setError("Enter Password");
            edtPassword.requestFocus();
        } else {
            User user = userQuery.findByUserEmailAndPassword(email, password);
            if (user != null) {
                if (user.getEmail().equals("admin@gmail.com")) {
                    Toast.makeText(LoginActivity.this, getString(R.string.login_successfully), Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("email");
                    editor.remove("password");
                    editor.remove("checked");
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, ManageActivity.class);
                    Common.currentUser = user;
                    startActivity(intent);
                    SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_USER_STATE, MODE_PRIVATE);
                    Utils.setPreferences(Common.currentUser, sharedPreferences);
                    finish();
                } else {
                    try {
                        Toast.makeText(LoginActivity.this, getString(R.string.login_successfully), Toast.LENGTH_SHORT).show();
                        if (cbRemember.isChecked()) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("email", email);
                            editor.putString("password", password);
                            editor.putBoolean("checked", true);
                            editor.commit();
                        } else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove("email");
                            editor.remove("password");
                            editor.remove("checked");
                            editor.commit();
                        }
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        Common.currentUser = user;
                        startActivity(intent);
                        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_USER_STATE, MODE_PRIVATE);
                        Utils.setPreferences(Common.currentUser, sharedPreferences);
                        finish();
                    } catch (Exception ex) {
                        Toast.makeText(this, getString(R.string.server_error, ex.getMessage()), Toast.LENGTH_SHORT).show();
                    }
                }

            } else {
                Toast.makeText(this, getString(R.string.email_or_password_incorrect), Toast.LENGTH_SHORT).show();
            }
        }
    }

        private void bidingView () {
            edtEmail = findViewById(R.id.edtEmail);
            edtPassword = findViewById(R.id.edtPassword);
            cbRemember = findViewById(R.id.cb_remember);
        }

        private boolean isValidEmail (String email){
            Pattern pattern;
            Matcher matcher;
            final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(email);
            return matcher.matches();
        }

    }