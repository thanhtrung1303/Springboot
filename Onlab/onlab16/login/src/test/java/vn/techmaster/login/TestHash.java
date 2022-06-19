package vn.techmaster.login;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.login.hash.Hashing;

import java.util.List;

@SpringBootTest
public class TestHash {
    @Autowired
    Hashing hashing;

    @Test
    public void hashPassword(){
        var passwords = List.of("122","121212","121212","3432");
        for (String password : passwords) {
            String hashPass = hashing.hashPassword(password);
            assertThat(hashPass).isNotNull();
        }
    }

    @Test
    public void validatePassword() {
        var passwords = List.of("122", "121212", "231231", "3432");
        for (String password : passwords) {
            String hashPass = hashing.hashPassword(password);
            hashing.validatePassword(password, hashPass);
            assertThat(hashing.validatePassword(password, hashPass)).isTrue();
        }
    }
}
