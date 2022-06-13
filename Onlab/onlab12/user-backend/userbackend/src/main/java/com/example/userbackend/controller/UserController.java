package com.example.userbackend.controller;

import com.example.userbackend.dto.UserDto;
import com.example.userbackend.model.User;
import com.example.userbackend.request.CreateUserRequest;
import com.example.userbackend.request.UpdatePasswordRequest;
import com.example.userbackend.request.UpdateUserRequest;
import com.example.userbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/users")
    public List<UserDto> getUser(){
        return userService.getUsers();
    }

    @GetMapping("/users/search")
    public List<UserDto> searchUser(@RequestParam String name){
        return userService.searchUser(name);
    }

    @DeleteMapping ("/users/{id}")
    public void deleteUser(@PathVariable int id){
         userService.deleteUser(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody CreateUserRequest request){
        return userService.createUser(request);
    }

    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public UserDto updateUser(@PathVariable int id, @RequestBody UpdateUserRequest request){
        return userService.updateUser(id, request);
    }

    @PutMapping("/users/{id}/update-password")
    public void updatePassword(@PathVariable int id, @RequestBody UpdatePasswordRequest request){
        userService.updatePassword(id, request);
    }

    @PostMapping("/users/{id}/forgot-password")
    public String updatePassword(@PathVariable int id){
        return userService.forgotPassword(id);
    }
    
    // upload file
    @PostMapping("/users/{id}/upload-file")
    public String uploadFile(@PathVariable int id, @ModelAttribute("file") MultipartFile file){
        return null;
    }
}
