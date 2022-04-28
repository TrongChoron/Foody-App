package vn.hcmute.nhom02.foody.mapper;

import android.database.Cursor;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Tue, 4/26/2022
 * Time     : 8:53 PM
 * Filename : RowMapper
 */
public interface RowMapper<T> {
    T mapRow(Cursor cs);
}
