package vn.techmaster.login.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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
    public User login(String email, String password) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if(!userOptional.isPresent()){
            return null;
        }
        User user = userOptional.get();
        return hashing.validatePassword(password, user.getHashedPassword()) ? user : null;
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
