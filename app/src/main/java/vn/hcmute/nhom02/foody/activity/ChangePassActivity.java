package vn.hcmute.nhom02.foody.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import vn.hcmute.nhom02.foody.Fragment.Menu.ProfileFragment;
import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.common.Common;
import vn.hcmute.nhom02.foody.common.Constants;
import vn.hcmute.nhom02.foody.common.Utils;
import vn.hcmute.nhom02.foody.database.IUserQuery;
import vn.hcmute.nhom02.foody.database.UserQuery;

public class ChangePassActivity extends AppCompatActivity {
    private TextInputEditText etPassword, etConfirmPassword;
    private final IUserQuery userQuery = UserQuery.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        binding();
    }

    public void btnChangePasswordClick(View view) {
        final String password = Objects.requireNonNull(etPassword.getText()).toString();
        final String confirmPassword = Objects.requireNonNull(etConfirmPassword.getText()).toString();
        if (password.isEmpty()) {
            etPassword.setError(getString(R.string.enter_password));
            etPassword.requestFocus();
        } else if (confirmPassword.isEmpty()) {
            etConfirmPassword.setError(getString(R.string.enter_confirmPass));
            etConfirmPassword.requestFocus();
        } else if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError(getString(R.string.password_mismatch));
            etConfirmPassword.requestFocus();
        } else {
            try {
                Common.currentUser.setPassword(password);
                Integer isUpdateSuccess = userQuery.updatePassword(Common.currentUser);
                if (isUpdateSuccess > 0) {
                    SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_USER_STATE, MODE_PRIVATE);
                    Toast.makeText(this, getString(R.string.change_password_succesfully), Toast.LENGTH_SHORT).show();
                    Utils.setPreferences(Common.currentUser, sharedPreferences);
                    startActivity(new Intent(ChangePassActivity.this, MainActivity.class));
                    finish();
                }
            } catch (Exception ex) {
                Toast.makeText(this, getString(R.string.server_error, ex.getMessage()), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void binding() {
        etPassword =findViewById(R.id.edtPassword);
        etConfirmPassword = findViewById(R.id.edtConfirmPassword);
    }
}