package vn.hcmute.nhom02.foody.Fragment.Menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import vn.hcmute.nhom02.foody.R;
import vn.hcmute.nhom02.foody.activity.LoginActivity;
import vn.hcmute.nhom02.foody.activity.MainActivity;
import vn.hcmute.nhom02.foody.activity.UpdateProfileActivity;
import vn.hcmute.nhom02.foody.common.Common;


public class ProfileFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


}