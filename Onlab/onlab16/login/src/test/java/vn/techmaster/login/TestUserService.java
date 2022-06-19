package vn.techmaster.login;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.login.model.User;
import vn.techmaster.login.service.UserService;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TestUserService {
    @Autowired
    private UserService userService;

    @Test
    public void addUser(){
        User user = userService.addUser("nguyen a", "a@gmail.com", "1234");

        assertThat(user).isNotNull();
    }
}
