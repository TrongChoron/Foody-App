package vn.hcmute.nhom02.foody.database;

import java.util.List;

import vn.hcmute.nhom02.foody.signup.User;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Tue, 4/26/2022
 * Time     : 8:56 PM
 * Filename : IUserQuery
 */
public interface IUserQuery extends GenericQuery<User> {
    Long insert(User user);

    Integer updatePassword(User user);

    Integer updateOnlyPhoto(User user);

    List<User> getAllUser();

    Integer updateProfile(User user);

    User findById(Integer id);

    User findByEmail(String email);

    User findByUserEmailAndPassword(String email, String password);
}
