package com.example.userbackend.service;

import com.example.userbackend.exception.BadRequestException;
import com.example.userbackend.util.Utils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

@Service
public class FileService {
    // Path folder de upload file
    private final Path rootPath =Paths.get("uploads");

    public FileService(){
        crearteFolder(rootPath.toString());
    }

    // tao folder
    public void crearteFolder(String path){
        File folder = new File(path);
        if(!folder.exists()){
            folder.mkdir();
        }
    }

    //upload file
    public String uploadFile(int id, MultipartFile file){

        // b1: tao folder tuong ung voi userId
        Path userDir = Paths.get("upload").resolve(String.valueOf(id));
        crearteFolder(userDir.toString());

        // b2: validate file
        validate(file);

        // b3: tao path tuong ung voi fileUpload
        String genarateFileId = String.valueOf(Instant.now().getEpochSecond());
        File fileServer = new File(userDir + "/" + genarateFileId);

        try {
            // su dung Bufer de luu du luu tu file
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileServer));

            stream.write(file.getBytes());
            stream.close();

            return "api/v1/users/" + id + "/file" + genarateFileId;
        }
        catch (Exception e){
            throw new RuntimeException("loi khi upload file");
        }
    }

    public void validate(MultipartFile file){
        // kiem tra ten file
        String fileName = file.getOriginalFilename();
        if(fileName == null || fileName.equals("")){
            throw new BadRequestException("ten file khong hop le");
        }

        // kiem tra duoi file
        String fileExtension = Utils.getExtensionFile(fileName);
        if(!Utils.checkFileExtension(fileExtension)){
            throw new BadRequestException("file khong hop le");
        }

        // kiem tra size (upload duoi 2mb)
        if(file.getSize()/ 1_000_000L > 2) {
            throw new BadRequestException("file khong duoc vuot qua 2mb");

        }
    }
}
