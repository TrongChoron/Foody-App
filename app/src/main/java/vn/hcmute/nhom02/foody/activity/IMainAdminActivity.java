package vn.hcmute.nhom02.foody.activity;

import androidx.fragment.app.Fragment;

public interface IMainAdminActivity {
    void loadFragment(Fragment fragment);
//    void gotoDetailFragment(Customer customer);
    void sendSms(String toPhoneNumber, String message);
//    void gotoDetailFragment(Driver driver);
}
