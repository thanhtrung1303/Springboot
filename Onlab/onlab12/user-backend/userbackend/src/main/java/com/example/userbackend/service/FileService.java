package com.example.userbackend.service;

import com.example.userbackend.exception.BadRequestException;
import com.example.userbackend.util.Utils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {
    // Path folder de upload file
    private final Path rootPath = Paths.get("uploads");

    public FileService() {
        crearteFolder(rootPath.toString());
    }

    // tao folder
    public void crearteFolder(String path) {
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    //upload file
    public String uploadFile(int id, MultipartFile file) {

        // b1: tao folder tuong ung voi userId
        Path userDir = Paths.get("uploads").resolve(String.valueOf(id));
        crearteFolder(userDir.toString());

        // b2: validate file
        validate(file);

        // b3: tao path tuong ung voi fileUpload
        String genarateFileId = String.valueOf(Instant.now().getEpochSecond());
        File fileServer = new File(userDir + "/" +  genarateFileId);

        try {
            // su dung Bufer de luu du luu tu file
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileServer));

            stream.write(file.getBytes());
            stream.close();

            return "api/v1/users/" + id + "/files/" + genarateFileId;
        } catch (Exception e) {
            throw new RuntimeException("loi khi upload file");
        }
    }

    public void validate(MultipartFile file) {
        // kiem tra ten file
        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.equals("")) {
            throw new BadRequestException("ten file khong hop le");
        }

        // kiem tra duoi file
        String fileExtension = Utils.getExtensionFile(fileName);
        if (!Utils.checkFileExtension(fileExtension)) {
            throw new BadRequestException("file khong hop le");
        }

        // kiem tra size (upload duoi 2mb)
        if ((double) file.getSize() / 1_000_000L > 2) {
            throw new BadRequestException("file khong duoc vuot qua 2mb");
        }
    }

    //xem file
    public byte[] readFile(int id, String fileId){
        // lay duong dan file tuong ung voi user_id
        Path userPath = rootPath.resolve(String.valueOf(id));

        // kiem tra userPath co ton tai hay khong
        if(!Files.exists(userPath)){
            throw new RuntimeException("loi khi doc file " + fileId);
        }
        try {
            Path file = userPath.resolve(fileId);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()){
                return StreamUtils.copyToByteArray(resource.getInputStream());
            } else {
                throw new RuntimeException("loi khi doc file " + fileId);
            }

        } catch (Exception e) {
            throw new RuntimeException("loi khi doc file " + fileId);
        }
    }

    // xoa file
    public void deleteFile(int id, String fileId){
        // lay duong dan file tuong ung voi user_id
        Path userPath = rootPath.resolve(String.valueOf(id));

        // kiem tra userPath co ton tai hay khong
        if(!Files.exists(userPath)){
            throw new RuntimeException("loi khi doc file " + fileId);
        }

        Path file = userPath.resolve(fileId);
        if(!file.toFile().exists()){
            throw new RuntimeException("file " + fileId + " khong ton tai");
        }
        file.toFile().delete();
    }

    // lay danh sach file
    public List<String> getFiles(int id) {
        // lay duong dan file tuong ung voi user_id
        Path userPath = rootPath.resolve(String.valueOf(id));

        // kiem tra userPath co ton tai hay khong
        if(!Files.exists(userPath)){
            return new ArrayList<>();
        }

        // lay danh sach file cua user
        File[] files = userPath.toFile().listFiles();
        return Arrays.stream(files)
                .map(file -> file.getName())
                .sorted((f1, f2) -> f2.compareTo(f1))
                .map(file -> "/api/v1/users" + id + "/files" + file)
                .collect(Collectors.toList());
    }
}
