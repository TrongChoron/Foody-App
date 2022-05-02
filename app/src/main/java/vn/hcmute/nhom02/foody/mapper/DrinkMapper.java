package vn.hcmute.nhom02.foody.mapper;

import android.database.Cursor;

import vn.hcmute.nhom02.foody.Domain.Drink;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Tue, 5/3/2022
 * Time     : 12:17 AM
 * Filename : DrinkMapper
 */
public class DrinkMapper implements RowMapper{
    @Override
    public Object mapRow(Cursor cs) {
        Drink drink = new Drink();
        drink.setId(cs.getInt(0));
        drink.setName(cs.getString(1));
        drink.setPic(cs.getString(2));
        drink.setPrice(cs.getString(3));
        drink.setType(cs.getString(4));
        return drink;
    }
}
