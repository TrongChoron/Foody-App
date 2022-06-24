package vn.hcmute.nhom02.foody.Fragment.Menu;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.InputStream;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.activity.ChangePassActivity;
import vn.hcmute.nhom02.foody.activity.LoginActivity;
import vn.hcmute.nhom02.foody.common.Common;
import vn.hcmute.nhom02.foody.common.Constants;
import vn.hcmute.nhom02.foody.common.Utils;
import vn.hcmute.nhom02.foody.database.IUserQuery;
import vn.hcmute.nhom02.foody.database.UserQuery;
import vn.hcmute.nhom02.foody.signup.User;


public class ProfileFragment extends Fragment {

    private TextView tvName, tvEmail,tvChangePass;
    private TextInputEditText edtName, edtPhone, edtAddress;
    private ImageButton ibCamera, ibFileUpload;
    private Button btnSave, btnLogOut;
    private CircleImageView ivProfile;
    private final IUserQuery userQuery = UserQuery.getInstance();
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        biding();

        setInfoUser();

        tvChangePass.setOnClickListener(view -> {
            startActivity(new Intent(getActivity().getApplicationContext(), ChangePassActivity.class));
        });

        btnSave.setOnClickListener(view -> {
            final String name = Objects.requireNonNull(edtName.getText()).toString();
            final String phone = Objects.requireNonNull(edtPhone.getText()).toString();
            final String address = Objects.requireNonNull(edtAddress.getText()).toString();
            Common.currentUser.setAvatar(Utils.convertImageViewToBytes(ivProfile));
            try {
                User user = userQuery.findById(Common.currentUser.getId());
                if (!user.getName().equals(name)
                        || !user.getPhone().equals(phone)
                        || !user.getAddress().equals(address)) {
                    updateProfile(user);
                    setInfoUser();
                } else {
                    updateOnlyPhoto(user);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnLogOut.setOnClickListener(view -> {
                xacNhanLogout();
//            Common.currentUser = null;
//            SharedPreferences userPreferences = this.requireContext().getSharedPreferences(Constants.SHARED_PREFERENCE_USER_STATE, MODE_PRIVATE);
//            userPreferences.edit().clear().apply();
//            startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
        });

        ibCamera.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, Constants.REQUEST_CODE_CAMERA);
        });

        ibFileUpload.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, Constants.REQUEST_CODE_FOLDER);
        });

        return view;
    }

    private void logout(){
        Common.currentUser = null;
        SharedPreferences userPreferences = this.requireContext().getSharedPreferences(Constants.SHARED_PREFERENCE_USER_STATE, MODE_PRIVATE);
        userPreferences.edit().clear().apply();
        startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
    }

    private void xacNhanLogout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setMessage("Do you want to Logout?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        logout();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void updateOnlyPhoto(User user) {
        try {
            if (user != null) {
                user.setAvatar(Common.currentUser.getAvatar());
                Integer updateUser = userQuery.updateOnlyPhoto(user);
                if (updateUser > 0) {
                    SharedPreferences sharedPreferences = this.requireContext().getSharedPreferences(Constants.SHARED_PREFERENCE_USER_STATE, MODE_PRIVATE);
                    Utils.setPreferences(Common.currentUser, sharedPreferences);
                    Toast.makeText(this.getContext(), R.string.update_profile_successfully, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this.getContext(), R.string.update_profile_failed, Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this.getContext(), getString(R.string.server_error, ex.getMessage()), Toast.LENGTH_SHORT).show();
        }

    }

    private void setInfoUser() {
        tvName.setText(Common.currentUser.getName());
        tvEmail.setText(Common.currentUser.getEmail());
        edtName.setText(Common.currentUser.getName());
        edtPhone.setText(Common.currentUser.getPhone());
        edtAddress.setText(Common.currentUser.getAddress());
        byte[] avatar = Common.currentUser.getAvatar();
        if (avatar != null) {
            ivProfile.setImageBitmap(BitmapFactory.decodeByteArray(avatar, 0, avatar.length));
        }
    }

    private void updateProfile(User user) {
        try {
            if (user != null) {
                final String name = Objects.requireNonNull(edtName.getText()).toString();
                final String phone = Objects.requireNonNull(edtPhone.getText()).toString();
                final String address = Objects.requireNonNull(edtAddress.getText()).toString();
                final byte[] avatar = Utils.convertImageViewToBytes(ivProfile);
                user.setName(name);
                user.setPhone(phone);
                user.setAddress(address);
                user.setAvatar(avatar);
                Integer updateUser = userQuery.updateProfile(user);
                if (updateUser > 0) {
                    Common.currentUser.setName(name);
                    Common.currentUser.setPhone(phone);
                    Common.currentUser.setAddress(address);
                    Common.currentUser.setAvatar(avatar);
                    SharedPreferences sharedPreferences = this.requireContext().getSharedPreferences(Constants.SHARED_PREFERENCE_USER_STATE, MODE_PRIVATE);
                    Utils.setPreferences(Common.currentUser, sharedPreferences);
                    Toast.makeText(this.getContext(), R.string.update_profile_successfully, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this.getContext(), R.string.update_profile_failed, Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this.getContext(), getString(R.string.server_error, ex.getMessage()), Toast.LENGTH_SHORT).show();
        }
    }

    private void biding() {
        tvName = view.findViewById(R.id.tv_fullname);
        tvEmail = view.findViewById(R.id.tv_email);
        edtName = view.findViewById(R.id.etName);
        edtPhone = view.findViewById(R.id.etPhone);
        edtAddress = view.findViewById(R.id.etAddress);
        btnSave = view.findViewById(R.id.btnSave);
        btnLogOut = view.findViewById(R.id.buttonLogout);
        ibCamera = view.findViewById(R.id.ib_camera);
        ibFileUpload = view.findViewById(R.id.ib_file_upload);
        ivProfile = view.findViewById(R.id.iv_profile);
        tvChangePass = view.findViewById(R.id.changePassTV);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.REQUEST_CODE_CAMERA && resultCode == getActivity().RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ivProfile.setImageBitmap(bitmap);
        }
        if (requestCode == Constants.REQUEST_CODE_FOLDER && resultCode == getActivity().RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(inputStream),
                        ivProfile.getWidth(), ivProfile.getHeight(), true);
                ivProfile.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void btnSaveClick(View view) {
        final String name = Objects.requireNonNull(edtName.getText()).toString();
        final String phone = Objects.requireNonNull(edtPhone.getText()).toString();
        final String address = Objects.requireNonNull(edtAddress.getText()).toString();
        Common.currentUser.setAvatar(Utils.convertImageViewToBytes(ivProfile));
        try {
            User user = userQuery.findById(Common.currentUser.getId());
            if (!user.getName().equals(name)
                    || !user.getPhone().equals(phone)
                    || !user.getAddress().equals(address)) {
                updateProfile(user);
                setInfoUser();
            } else {
                updateOnlyPhoto(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}