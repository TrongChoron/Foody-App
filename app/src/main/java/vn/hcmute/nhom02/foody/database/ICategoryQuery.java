package vn.hcmute.nhom02.foody.database;

import vn.hcmute.nhom02.foody.Domain.CategoryModel;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Thu, 5/12/2022
 * Time     : 00:01
 * Filename : ICategoryQuery
 */
public interface ICategoryQuery extends GenericQuery<CategoryModel> {
    Long insert(CategoryModel categoryModel);

    CategoryModel findByCode(String code);

    CategoryModel findById(Integer id);
}
