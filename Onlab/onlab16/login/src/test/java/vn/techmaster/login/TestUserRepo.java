package vn.techmaster.login;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.login.model.State;
import vn.techmaster.login.model.User;
import vn.techmaster.login.respository.UserRepo;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TestUserRepo {
    @Test
    public void addUser(){
        UserRepo userRepo = new UserRepo();
        User user = userRepo.addUser("le van a", "asdf@gmail.com", "123456",
                State.PEDDING);
        assertThat(user).isNotNull();
        System.out.println("id= " + user.getId());
        assertThat(user.getId()).isNotNull();
        assertThat(user.getState()).isEqualTo(State.PEDDING);
    }

    @Test
    public void isEmailExist() {
        UserRepo userRepo = new UserRepo();
        userRepo.addUser("nguyen van a", "abc@gmail.com", "1234",State.PEDDING);

        assertThat(userRepo.isEmailExist("abc@gmail.com")).isTrue();
    }

    @Test
    public void findByEmail(){
        UserRepo userRepo = new UserRepo();
        userRepo.addUser("nguyen van a", "abc@gmail.com", "1234",State.PEDDING);

        assertThat(userRepo.findByEmail("abc@gmail.com")).isPresent();
        assertThat(userRepo.findByEmail("abc6@gmail.com")).isNotPresent();
        
    }

}
