package com.example.onlab19;

import java.util.List;

import com.example.onlab19.dto.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import com.example.onlab19.entity.User;
import com.example.onlab19.repository.UseRepository;

@SpringBootTest
public class TestUserRepository {


    @Autowired
    private UseRepository userRepository;

    @Test
    void insertUserTest() {
        User user = new User(1, "dangkhoa", "anhkhoa98nd@gmail.com", null);
        User user1 = new User(2, "nguyen huong", "nguyenhuong98nd@gmail.com", null);
        User user2 = new User(3, "ba sang", "basang@gmail.com", null);

        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    void countByNameContainsIgnoreCaseTest() {
        Long count = userRepository.countByNameContainsIgnoreCase("dangkhoa");
        Assertions.assertThat(count).isEqualTo(1L);
    }

    @Test
    void findByOrderByNameAscTest() {
        List<User> users = userRepository.findByOrderByNameAsc(PageRequest.of(0, 2));
        users.forEach(user -> System.out.println(user.getName()));
    }

    @Test
    void findByEmailReturnDtoTest() {
        UserDto userDto = userRepository.findByEmail("anhkhoa98nd@gmail.com");
        Assertions.assertThat(userDto.getEmail()).isEqualTo("dangkhoa");
    }
    
}
