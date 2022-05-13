package vn.hcmute.nhom02.foody.signup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.activity.LoginActivity;
import vn.hcmute.nhom02.foody.common.Constants;
import vn.hcmute.nhom02.foody.common.Utils;
import vn.hcmute.nhom02.foody.database.IUserQuery;
import vn.hcmute.nhom02.foody.database.UserQuery;

public class RegisterActivity extends AppCompatActivity {

    private ImageView ivProfile;
    private EditText etName, etEmail, etPassword, etConfirmPassword;
    private final IUserQuery userQuery = UserQuery.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        biding();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ivProfile.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public void buttonSignUpClick(View view) {
        final String name, email, password, confirmPassword;
        name = Objects.requireNonNull(etName.getText()).toString();
        email = Objects.requireNonNull(etEmail.getText()).toString();
        password = Objects.requireNonNull(etPassword.getText()).toString();
        confirmPassword = Objects.requireNonNull(etConfirmPassword.getText()).toString();

        if (name.isEmpty()) {
            etName.setError(getString(R.string.enter_name));
            etName.requestFocus();
        } else if (email.isEmpty()) {
            etEmail.setError(getString(R.string.enter_email));
            etEmail.requestFocus();
        } else if (!emailValidator(email)) {
            etEmail.setError(getString(R.string.email_invalidate));
            etEmail.requestFocus();
        }else if (checkEmailExisted(email)) {
            etEmail.setError(getString(R.string.email_existed));
            etEmail.requestFocus();
        } else if (password.isEmpty()) {
            etPassword.setError(getString(R.string.enter_password));
            etPassword.requestFocus();
        } else if (confirmPassword.isEmpty()) {
            etConfirmPassword.setError(getString(R.string.enter_password));
            etConfirmPassword.requestFocus();
        } else if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError(getString(R.string.password_mismatch));
            etConfirmPassword.requestFocus();
        } else {
            try {
                SharedPreferences sharedPreferences = getSharedPreferences(Constants.DATA_LOGIN, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("email", email);
                editor.putString("password", password);
                editor.putBoolean("checked", true);
                editor.commit();
                User user = new User(null, name, email, password,"","", null);
                userQuery.insert(user);
                Toast.makeText(this, getString(R.string.sign_up_successfully), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            } catch (Exception ex) {
                Toast.makeText(this, getString(R.string.sign_up_failed, ex.getMessage()), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void biding() {
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        ivProfile = findViewById(R.id.ivProfile);
    }

    private boolean emailValidator(final String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean checkEmailExisted(String email) {
        User user = userQuery.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
}