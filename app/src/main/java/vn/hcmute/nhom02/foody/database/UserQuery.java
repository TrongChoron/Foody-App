package vn.hcmute.nhom02.foody.database;

import java.util.List;

import vn.hcmute.nhom02.foody.mapper.UserMapper;
import vn.hcmute.nhom02.foody.signup.User;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Tue, 4/26/2022
 * Time     : 8:59 PM
 * Filename : UserQuery
 */
public class UserQuery extends AbstractQuery<User> implements IUserQuery {
    private static UserQuery instance = null;

    public static UserQuery getInstance() {
        if (instance == null) {
            instance = new UserQuery();
        }
        return instance;
    }

    @Override
    public Long insert(User user) {
        final String sql = "INSERT INTO user VALUES(null, ?, ?, ?, ?)";
        return insert(sql, user.getName(), user.getEmail(), user.getPassword(), user.getAvatar());
    }

    @Override
    public Integer updatePassword(User user) {
        final String sql = "UPDATE user SET password = ? WHERE id = ?";
        return update(sql, user.getPassword(), user.getId());
    }

    @Override
    public Integer updateOnlyPhoto(User user) {
        final String sql = "UPDATE user SET avatar = ? WHERE id = ?";
        return update(sql, user.getAvatar(), user.getId());
    }

    @Override
    public Integer updatePhotoAndName(User user) {
        final String sql = "UPDATE user SET avatar = ? , name = ? WHERE id = ?";
        return update(sql, user.getAvatar(), user.getName(), user.getId());
    }

    @Override
    public User findById(Integer id) {
        final String sql = "SELECT * FROM user WHERE id = " + id;
        return findById(sql, new UserMapper());
    }

    @Override
    public User findByEmail(String email) {
        final String sql = "SELECT * FROM user WHERE email = '" + email + "' ";
        List results = query(sql, new UserMapper());
        return results.size() > 0 ? (User) results.get(0) : null;
    }

    @Override
    public User findByUserEmailAndPassword(String email, String password) {
        final String sql = "SELECT * FROM user WHERE email = '" + email + "' and password = '" + password + "' ";
        final List results = query(sql, new UserMapper());
        return results.size() > 0 ? (User) results.get(0) : null;
    }
}