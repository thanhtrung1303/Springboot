package vn.techmaster.color.service;

import org.springframework.stereotype.Service;

import vn.techmaster.color.exception.BadRequestException;

@Service
public class BmiService {
    public double caculateBmi(double height, double weight) {
        if (height <= 0 || weight <= 0) {
            throw new BadRequestException("Khong hop le");
        }
        return height / (weight * weight);
    }
}
