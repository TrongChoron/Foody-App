package vn.hcmute.nhom02.foody.Domain;

import androidx.annotation.NonNull;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Thu, 5/12/2022
 * Time     : 00:00
 * Filename : CategoryModel
 */
public class CategoryModel {
    private Integer id;
    private String name;
    private String code;

    public CategoryModel() {
    }

    public CategoryModel(Integer id, String name,String code) {
        this.id = id;
        this.name = name;
        this.code= code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @NonNull
    @Override
    public String toString() {
        return "CategoryModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
