package vn.hcmute.nhom02.foody.database;

import java.util.List;

import vn.hcmute.nhom02.foody.mapper.RowMapper;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Tue, 4/26/2022
 * Time     : 8:52 PM
 * Filename : GenericQuery
 */
public interface GenericQuery<T> {
    List<T> query(String sql, RowMapper<T> rowMapper);

    Long insert(String sql, Object... parameters);

    Integer update(String sql, Object... parameters);

    T findById(String sql, RowMapper<T> rowMapper);

    Integer delete(String sql, Integer id);
}
