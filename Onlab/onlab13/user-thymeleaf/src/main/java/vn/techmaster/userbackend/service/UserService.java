package vn.techmaster.userbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.techmaster.userbackend.dto.UserDto;
import vn.techmaster.userbackend.exception.BadRequestException;
import vn.techmaster.userbackend.exception.NotFoundException;
import vn.techmaster.userbackend.mapper.UserMapper;
import vn.techmaster.userbackend.model.User;
import vn.techmaster.userbackend.request.CreateUserRequest;
import vn.techmaster.userbackend.request.UpdatePasswordRequest;
import vn.techmaster.userbackend.request.UpdateUserRequest;

@Service
public class UserService {
    @Autowired
    FileService fileService;

    private Random rd = new Random();

    private List<User> users;

    public UserService() {
        initData();
    }

    public void initData() {
        users = new ArrayList<>();
        users.add(new User(1, "Nguyễn Văn A", "a@gmail.com", "0123456789", "Tỉnh Thái Bình", null, "111"));
        users.add(new User(2, "Trần Văn B", "b@gmail.com", "0123456789", "Tỉnh Nam Định", null, "222"));
        users.add(new User(3, "Ngô Thị C", "c@gmail.com", "0123456789", "Tỉnh Hưng Yên", null, "333"));
    }

    // Lấy danh sách user
    public List<UserDto> getUsers() {
        return users.stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    // Tìm kiếm user theo tên
    public List<UserDto> searchUsers(String name) {
        return users.stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    // Xoá user theo id
    public void deleteUser(int id) {
        if (findUser(id).isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }
        users.removeIf(user -> user.getId() == id);
    }

    // Tạo user mới
    public UserDto createUser(CreateUserRequest request) {
        // Kiểm tra email đã tồn tại hay chưa
        if (findUser(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email = " + request.getEmail() + " đã tồn tại");
        }

        // Tạo user mới
        User user = new User();
        user.setId(rd.nextInt(100 - 4 + 1) + 4); // Random id từ 4 -> 100
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setPassword(request.getPassword());

        users.add(user);

        return UserMapper.toUserDto(user);
    }

    // Lấy thông tin user theo id
    public UserDto getUserById(int id) {
        Optional<User> userOptional = findUser(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }

        return UserMapper.toUserDto(userOptional.get());
    }

    // Cập nhật thông tin user
    public UserDto updateUser(int id, UpdateUserRequest request) {
        Optional<User> userOptional = findUser(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }

        User user = userOptional.get();
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());

        return UserMapper.toUserDto(user);
    }

    // Thay đổi password
    public void updatePassword(int id, UpdatePasswordRequest request) {
        Optional<User> userOptional = findUser(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }

        User user = userOptional.get();

        // Kiểm tra oldPassword có chính xác hay không
        if (!user.getPassword().equals(request.getOldPassword())) {
            throw new BadRequestException("Mật khẩu cũ không chính xác");
        }

        // Kiểm tra oldPassword = newPassword hay không
        if (request.getOldPassword().equals(request.getNewPassword())) {
            throw new BadRequestException("Mật khẩu cũ và mật khẩu mới không được trùng nhau");
        }

        user.setPassword(request.getNewPassword());
    }

    public String forgotPassword(int id) {
        Optional<User> userOptional = findUser(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }

        User user = userOptional.get();

        String newPassword = String.valueOf(rd.nextInt(1000 - 100) + 100);
        user.setPassword(newPassword);
        return newPassword;
    }

    // Upload file
    public String uploadFile(int id, MultipartFile file) {
        if (findUser(id).isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }

        return fileService.uploadFile(id, file);
    }

    // Xem file
    public byte[] readFile(int id, String fileId) {
        return fileService.readFile(id, fileId);
    }

    // Xoá file
    public void deleteFile(int id, String fileId) {
        fileService.deleteFile(id, fileId);
    }

    // Lấy danh sách file upload
    public List<String> getFiles(int id) {
        if (findUser(id).isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }

        return fileService.getFiles(id);
    }

    // Update avatar
    public UserDto updateAvatar(int id, String fileId) {
        Optional<User> userOptional = findUser(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }

        if (!fileService.checkFileIdExist(id, fileId)) {
            throw new NotFoundException("Không tồn tại file " + fileId);
        }

        User user = userOptional.get();
        user.setAvatar(fileId);

        return UserMapper.toUserDto(user);
    }

    // HELPER METHOD
    public Optional<User> findUser(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public Optional<User> findUser(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }
}
