package vn.hcmute.nhom02.foody.common;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;

import vn.hcmute.nhom02.foody.signup.User;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Tue, 4/26/2022
 * Time     : 8:46 PM
 * Filename : Utils
 */
public class Utils {
    public static String formatCurrenCy(float price) {
        String pattern = "###,###.##";
        return new DecimalFormat(pattern).format(price);
    }

    public static void setPreferences(User user, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("user", json);
        editor.apply();
    }

    public static void getPreferences(SharedPreferences sharedPreferences) {
        if (sharedPreferences != null) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString("user", "");
            Common.currentUser = gson.fromJson(json, User.class);
        }
    }

    public static byte[] convertImageViewToBytes(ImageView imageView) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap convertBytesToBitMap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static Bitmap convertUriToBitmap(Intent data, ContentResolver contentResolver) {
        Uri uri = data.getData();
        try {
            InputStream inputStream = contentResolver.openInputStream(uri);
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception ex) {
            return null;
        }
    }
}
