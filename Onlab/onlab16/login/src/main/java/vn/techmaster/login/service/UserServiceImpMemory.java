package vn.techmaster.login.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import vn.techmaster.login.exception.UserException;
import vn.techmaster.login.hash.Hashing;
import vn.techmaster.login.model.State;
import vn.techmaster.login.model.User;
import vn.techmaster.login.respository.UserRepo;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpMemory implements UserService{
    private UserRepo userRepo;
    private Hashing hashing;

@Override
  public User login(String email, String password){
    Optional<User> o_user = userRepo.findByEmail(email);
    //Nếu user không tồn tại thì báo lỗi
    if (!o_user.isPresent()) {
      throw new UserException("User is not found");
    }

    User user = o_user.get();
    //User muốn login phải có trạng thái Active
    if (user.getState() != State.ACTIVE) {
      throw new UserException("User is not activated");
    }

    //Kiểm tra password
    if (hashing.validatePassword(password, user.getHashedPassword() )) {
      return user;
    } else {
      throw new UserException("Password is incorrect");
    }  
  }

    @Override
    public Boolean logout(String email) {
        return null;
    }

    @Override
    public User addUser(String fullName, String email, String password) {
        return userRepo.addPendingUser(fullName, email, hashing.hashPassword(password));
    }

    @Override
    public User addActiveUser(String fullName, String email, String password) {
        return userRepo.addUser(fullName, email, hashing.hashPassword(password), State.ACTIVE);
    }

    @Override
    public Boolean activateUser(String activation_code) {
        return null;
    }

    @Override
    public Boolean updatePassword(String email, String password) {
        return null;
    }

    @Override
    public Boolean updateEmail(String email, String newEmail) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User findById(String id) {
        return null;
    }
}
