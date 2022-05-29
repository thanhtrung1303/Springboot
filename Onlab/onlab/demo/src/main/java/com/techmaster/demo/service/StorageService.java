package com.techmaster.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.techmaster.demo.exception.StorageException;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
    @Value("${upload.path}")
    private String path;

    public String saveFile(MultipartFile file, String id) throws IOException {
        if (file.isEmpty()) {
            throw new StorageException("Fail to store empty file");
        }
        String extension = getFileExtension(file.getOriginalFilename());
        String newFileName = path + id + "." + extension;
        try{
            var is = file.getInputStream();
            Files.copy(is, Paths.get(newFileName), StandardCopyOption.REPLACE_EXISTING);
            return id + "." + extension;
        } catch (IOException e) {
            var msg = String.format("Failed to store file %s", newFileName);
            throw new StorageException(msg, e);
        }
    }
    private String getFileExtension(String fileName) {
        int postOfDot = fileName.lastIndexOf(".");
        if(postOfDot >= 0) {
            return fileName.substring(postOfDot+1);
        } else {
            return null;
        }
    }
}
