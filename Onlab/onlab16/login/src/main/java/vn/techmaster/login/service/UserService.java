package vn.techmaster.login.service;

import vn.techmaster.login.model.User;

import java.util.Optional;

public interface UserService {
    public User login(String email, String password);
    public Boolean logout(String email);
    //
    public User addUser(String fullName, String email, String password);
    public User addActiveUser(String fullName, String email, String password);
    public Boolean activateUser(String activation_code);
    //
    public Boolean updatePassword(String email, String password);
    public Boolean updateEmail(String email, String newEmail);
    //
    public Optional<User> findByEmail(String email);
    public User findById(String id);
}
