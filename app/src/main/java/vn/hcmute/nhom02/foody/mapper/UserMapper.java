package vn.hcmute.nhom02.foody.mapper;

import android.database.Cursor;

import vn.hcmute.nhom02.foody.signup.User;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Tue, 4/26/2022
 * Time     : 8:54 PM
 * Filename : UserMapper
 */
public class UserMapper implements RowMapper{
    @Override
    public Object mapRow(Cursor cs) {
        User user = new User();
        user.setId(cs.getInt(0));
        user.setName(cs.getString(1));
        user.setEmail(cs.getString(2));
        user.setPassword(cs.getString(3));
        user.setPhone(cs.getString(4));
        user.setAddress(cs.getString(5));
        user.setAvatar(cs.getBlob(6));
        return user;
    }
}
