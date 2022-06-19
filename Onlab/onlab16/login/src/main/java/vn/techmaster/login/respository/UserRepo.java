package vn.techmaster.login.respository;

import org.springframework.stereotype.Repository;
import vn.techmaster.login.model.State;
import vn.techmaster.login.model.User;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepo {
    private ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    public User addPendingUser(String fullName, String email, String hasedPassword){
        return addUser(fullName, email, hasedPassword, State.PEDDING);
    }

    public User addUser(String fullName, String email, String hashedPassword, State state){
        String id = UUID.randomUUID().toString();
        User user = User.builder()
                .id(id).fullname(fullName)
                .email(email)
                .hashedPassword(hashedPassword)
                .state(state).build();

        users.put(id, user);

        return user;
    }

    public boolean isEmailExist(String email){
        return users.values().stream()
                .anyMatch(o1 -> o1.getEmail().equalsIgnoreCase(email));
    }

    public Optional<User> findByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst();
    }



}
