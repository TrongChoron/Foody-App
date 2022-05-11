package vn.hcmute.nhom02.foody.mapper;

import android.database.Cursor;

import vn.hcmute.nhom02.foody.Domain.CategoryModel;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Thu, 5/12/2022
 * Time     : 00:04
 * Filename : CategoryMapper
 */
public class CategoryMapper implements RowMapper<CategoryModel> {
    @Override
    public CategoryModel mapRow(Cursor cs) {
        CategoryModel category = new CategoryModel();
        category.setId(cs.getInt(0));
        category.setName(cs.getString(1));
        category.setCode(cs.getString(2));
        return category;
    }
}
