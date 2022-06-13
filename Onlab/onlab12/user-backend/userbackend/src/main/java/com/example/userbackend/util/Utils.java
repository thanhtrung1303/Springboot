package com.example.userbackend.util;

import java.util.Arrays;
import java.util.List;

public class Utils {
    // lay extension file
    // image.png, avatar.jpg => png, jpg

    public static String getExtensionFile(String fileName){
        int lastIndexof = fileName.lastIndexOf(".");
        if(lastIndexof == -1){
            return "";
        }
        return fileName.substring(lastIndexof + 1);
    }

    // kiem tra extension file co nam trong danh sach upload hay khong?
    public static boolean checkFileExtension(String fileExtension){
        List<String> fileExtensions = Arrays.asList("png", "jpg", "jpeg");
        return fileExtensions.contains(fileExtension);
    }
}
