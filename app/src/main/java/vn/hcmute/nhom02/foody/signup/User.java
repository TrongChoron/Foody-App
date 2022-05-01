package vn.hcmute.nhom02.foody.signup;

import androidx.annotation.NonNull;

import java.util.Arrays;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Tue, 4/26/2022
 * Time     : 8:45 PM
 * Filename : User
 */
public class User {
    private Integer id;
    private String name;
    private String email;
    private String password;

    public User(){

    }

    public User(Integer id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
