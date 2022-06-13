package com.example.userbackend.service;

import com.example.userbackend.dto.UserDto;
import com.example.userbackend.exception.BadRequestException;
import com.example.userbackend.exception.NotFoundException;
import com.example.userbackend.mapper.UserMapper;
import com.example.userbackend.model.User;
import com.example.userbackend.request.CreateUserRequest;
import com.example.userbackend.request.UpdatePasswordRequest;
import com.example.userbackend.request.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService {
    private List<User> users;

    @Autowired
    private FileService fileService;

    public UserService() {
        initData();
    }

    public void initData() {
        users = new ArrayList<>();
        users.add(new User(1, "nguyen van a", "a@gmail.com", "0921872187", "Thành phố Hà Nội", null, "111"));
        users.add(new User(2, "nguyen van b", "b@gmail.com", "0921872187", "Thành phố Đà Nẵng", null, "222"));
        users.add(new User(3, "nguyen van c", "c@gmail.com", "0921872187", "Thành phố Hồ Chí Minh", null, "333"));
    }

    public List<UserDto> getUsers() {
        return users.stream().map(user -> UserMapper.toUserDto(user)).collect(Collectors.toList());
    }

    public List<UserDto> searchUser(String name) {
        return users
                .stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .map(user -> UserMapper.toUserDto(user)).collect(Collectors.toList());
    }

    public void deleteUser(int id) {
        if (findUser(id).isEmpty()) {
            throw new NotFoundException("khong ton tai user co id " + id);
        }
        users.removeIf(user -> user.getId() == id);
    }

    public UserDto createUser(CreateUserRequest request) {
        // kiem tra email da ton tai hay chua
        if (findUser(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email = " + request.getEmail() + "da ton tai ");
        }
        // tao user moi
        Random rd = new Random();
        User user = new User();
        user.setId(rd.nextInt(100 - 4 + 1) + 4); // lay random id tu 4 den 100
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setPassword(request.getPassword());

        users.add(user);

        return UserMapper.toUserDto(user);
    }

    // lay user theo id
    public UserDto getUserById(int id) {
        Optional<User> userOptional = findUser(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("khong ton tai id " + id);
        }
        return UserMapper.toUserDto(userOptional.get());
    }

    // cap nhat
    public UserDto updateUser(int id, UpdateUserRequest request) {
        Optional<User> userOptional = findUser(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("khong ton tai id " + id);
        }
        User user = userOptional.get();
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());

        return UserMapper.toUserDto(user);
    }

    public void updatePassword(int id, UpdatePasswordRequest request) {
        Optional<User> userOptional = findUser(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("khong ton tai id " + id);
        }
        User user = userOptional.get();

        // kiem tra password cu co dung hay khong?
        if (!user.getPassword().equals(request.getOldPassword())) {
            throw new BadRequestException("mat khau cu khong chinh xac");
        }

        // kiem tra oldpassword = newpassword hay khong?
        if (request.getNewPassword().equals(request.getOldPassword())) {
            throw new BadRequestException("mat khau cu va moi khong duoc trung nhau");
        }
        user.setPassword(request.getNewPassword());
    }

    // quen mat khau
    public String forgotPassword(int id) {
        Optional<User> userOptional = findUser(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("khong ton tai id " + id);
        }

        // lay ra thong tin cua user trong optinal
        User user = userOptional.get();

        // tao mat khau moi
        Random rd = new Random();
        String password = String.valueOf(rd.nextInt((1000 - 100) + 100));
        user.setPassword(password);

        return password;
    }
    public String uploadFile(@PathVariable int id, MultipartFile file) {
        Optional<User> userOptional = findUser(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("khong ton tai id " + id);
        }
        return fileService.uploadFile(id, file);
    }

    // xem file


    public Optional<User> findUser(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public Optional<User> findUser(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }


}
