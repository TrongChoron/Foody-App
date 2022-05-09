package vn.hcmute.nhom02.foody.Fragment.Menu;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.activity.LoginActivity;
import vn.hcmute.nhom02.foody.activity.MainActivity;
import vn.hcmute.nhom02.foody.activity.UpdateProfileActivity;
import vn.hcmute.nhom02.foody.common.Common;
import vn.hcmute.nhom02.foody.common.Constants;
import vn.hcmute.nhom02.foody.common.Utils;
import vn.hcmute.nhom02.foody.database.IUserQuery;
import vn.hcmute.nhom02.foody.database.UserQuery;
import vn.hcmute.nhom02.foody.signup.User;


public class ProfileFragment extends Fragment {

    private EditText edtEmail, edtName;
    private final IUserQuery userQuery = UserQuery.getInstance();
    private Button btnSave,btnLogOut;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        biding();
        edtEmail.setText(Common.currentUser.getEmail());
        edtName.setText(Common.currentUser.getName());

        btnSave.setOnClickListener(view -> {
            final String name = Objects.requireNonNull(edtName.getText()).toString();
            final String email = Objects.requireNonNull(edtEmail.getText()).toString();
            try {
                User user = userQuery.findById(Common.currentUser.getId());
                if (!user.getName().equals(name) || !user.getEmail().equals(email)) {
                    updateNameAndEmail(user);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnLogOut.setOnClickListener(view -> {
            Common.currentUser = null;
            SharedPreferences userPreferences = this.requireContext().getSharedPreferences(Constants.SHARED_PREFERENCE_USER_STATE, MODE_PRIVATE);
            userPreferences.edit().clear().apply();
            startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
        });

        return view;
    }

    private void updateNameAndEmail(User user) {
        try{
            if (user != null) {
                final String name = Objects.requireNonNull(edtName.getText()).toString();
                final String email = Objects.requireNonNull(edtEmail.getText()).toString();
                user.setName(name);
                user.setEmail(email);
                Integer updateUser = userQuery.updateNameAndEmail(user);
                if (updateUser > 0) {
                    Common.currentUser.setName(name);
                    Common.currentUser.setEmail(email);
                    SharedPreferences sharedPreferences = this.requireContext().getSharedPreferences(Constants.SHARED_PREFERENCE_USER_STATE, MODE_PRIVATE);
                    Utils.setPreferences(Common.currentUser, sharedPreferences);
                    Toast.makeText(this.getContext(), R.string.update_profile_successfully, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this.getContext(), R.string.update_profile_failed, Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this.getContext(), getString(R.string.server_error, ex.getMessage()), Toast.LENGTH_SHORT).show();
        }
    }

    private void biding() {
        edtName = view.findViewById(R.id.etName);
        edtEmail = view.findViewById(R.id.etEmail);
        btnSave = view.findViewById(R.id.btnSave);
        btnLogOut = view.findViewById(R.id.buttonLogout);
    }

}