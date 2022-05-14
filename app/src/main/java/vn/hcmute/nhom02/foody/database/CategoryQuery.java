package vn.hcmute.nhom02.foody.database;

import java.util.List;

import vn.hcmute.nhom02.foody.Domain.CategoryModel;
import vn.hcmute.nhom02.foody.mapper.CategoryMapper;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Thu, 5/12/2022
 * Time     : 00:02
 * Filename : CategoryQuery
 */
public class CategoryQuery extends AbstractQuery<CategoryModel> implements ICategoryQuery {

    private static ICategoryQuery instance = null;

    public static ICategoryQuery getInstance() {
        if (instance == null) {
            instance = new CategoryQuery();
        }
        return instance;
    }

    @Override
    public Long insert(CategoryModel categoryModel) {
        final String sql = "INSERT INTO category VALUES(null, ?, ?)";
        return insert(sql, categoryModel.getName(),categoryModel.getCode());
    }

    @Override
    public List<CategoryModel> findAllCategory() {
        final String sql = "SELECT * FROM category";
        return query(sql, new CategoryMapper());
    }

    @Override
    public Integer delete(Integer id) {
        final String sql = "DELETE FROM category WHERE id = ?";
        return delete(sql,id);
    }

    @Override
    public CategoryModel findByName(String name) {
        final String sql = "SELECT * FROM category WHERE name = '" + name + "' ";
        List results = query(sql, new CategoryMapper());
        return results.size() > 0 ? (CategoryModel) results.get(0) : null;
    }


    @Override
    public CategoryModel findByCode(String code) {
        final String sql = "SELECT * FROM category WHERE code = '" + code + "' ";
        List results = query(sql, new CategoryMapper());
        return results.size() > 0 ? (CategoryModel) results.get(0) : null;
    }

    @Override
    public CategoryModel findById(Integer id) {
        final String sql = "SELECT * FROM category WHERE id = " + id;
        return findById(sql, new CategoryMapper());
    }
}
