package vn.hcmute.nhom02.foody.signup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.activity.LoginActivity;
import vn.hcmute.nhom02.foody.common.Constants;
import vn.hcmute.nhom02.foody.database.IUserQuery;
import vn.hcmute.nhom02.foody.database.UserQuery;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etConfirmPassword;
    private final IUserQuery userQuery = UserQuery.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        biding();
    }

    public void buttonLoginClick(View view) {
        final String name, email, password, confirmPassword;
        name = Objects.requireNonNull(etName.getText()).toString();
        email = Objects.requireNonNull(etEmail.getText()).toString();
        password = Objects.requireNonNull(etPassword.getText()).toString();
        confirmPassword = Objects.requireNonNull(etConfirmPassword.getText()).toString();

        if (name.isEmpty()) {
        } else if (email.isEmpty()) {
            etEmail.setError(getString(R.string.enter_email));
        } else if (!emailValidator(email)) {
            etEmail.setError(getString(R.string.email_invalidate));
        } else if (password.isEmpty()) {
            etPassword.setError(getString(R.string.enter_password));
        } else if (confirmPassword.isEmpty()) {
            etConfirmPassword.setError(getString(R.string.enter_password));
        } else if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError(getString(R.string.password_mismatch));
        } else {
            try {
                User user = new User(null, name, email, password);
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
    }

    private boolean emailValidator(final String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}