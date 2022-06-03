package vn.techmaster.color.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import vn.techmaster.color.exception.BadRequestException;

@Service
public class ColorService {
    public String randomColor(int type) {
        return switch (type) {
            case 1 -> randomColorName();
            case 2 -> randomHexColor();
            case 3 -> randomRgbColor();
            default -> throw new BadRequestException("type = " + type + " Khong hop le");
        };
    }

    public String randomColorName() {
        String[] colors = { "red", "green", "blue", "black" };
        Random rd = new Random();
        String colorName = colors[rd.nextInt(colors.length)];
        return colorName;
    }

    public String randomHexColor() {
        String[] letters = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
        String color = "#";
        for (int i = 0; i < 6; i++) {
            color += letters[(int) Math.round(Math.random() * 15)];
        }
        return color;
    }

    public String randomRgbColor() {
        Random numGen = new Random();
        return new String(numGen.nextInt(256) + ", " + numGen.nextInt(256) + ", " + numGen.nextInt(256));
    }
}
