package vn.techmaster.userbackend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import vn.techmaster.userbackend.dto.UserDto;
import vn.techmaster.userbackend.request.CreateUserRequest;
import vn.techmaster.userbackend.request.UpdatePasswordRequest;
import vn.techmaster.userbackend.request.UpdateUserRequest;
import vn.techmaster.userbackend.service.UserService;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/search")
    public List<UserDto> searchUsers(@RequestParam String name) {
        return userService.searchUsers(name);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public UserDto updateUser(@PathVariable int id, @RequestBody UpdateUserRequest request) {
        return userService.updateUser(id, request);
    }

    @PutMapping("/users/{id}/update-password")
    public void updatePassword(@PathVariable int id, @RequestBody UpdatePasswordRequest request) {
        userService.updatePassword(id, request);
    }

    @PutMapping("/users/{id}/forgot-password")
    public String forgotPassword(@PathVariable int id) {
        return userService.forgotPassword(id);
    }

    // Upload file
    @PostMapping("/users/{id}/upload-file")
    public String uploadFile(@PathVariable int id, @ModelAttribute("file") MultipartFile file) {
        return userService.uploadFile(id, file);
    }

    // Xem file
    @GetMapping(value = "/users/{id}/files/{fileId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] readFile(@PathVariable int id, @PathVariable String fileId) {
        return userService.readFile(id, fileId);
    }

    // Xoá file
    @DeleteMapping("/users/{id}/files/{fileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFile(@PathVariable int id, @PathVariable String fileId) {
        userService.deleteFile(id, fileId);
    }

    // Lấy danh sách file upload
    @GetMapping("/users/{id}/files")
    public List<String> getFiles(@PathVariable int id) {
        return userService.getFiles(id);
    }

    // Update avatar
    @PutMapping("/users/{id}/update-avatar")
    public UserDto updateAvatar(@PathVariable int id, @RequestParam String fileId) {
        return userService.updateAvatar(id, fileId);
    }
}
