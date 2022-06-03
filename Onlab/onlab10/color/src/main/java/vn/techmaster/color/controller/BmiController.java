package vn.techmaster.color.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import vn.techmaster.color.request.BmiRequest;
import vn.techmaster.color.service.BmiService;

@RestController
@AllArgsConstructor
public class BmiController {
    private final BmiService bmiService;

    @GetMapping("/bmi-get")
    public double caculateBmiGet(@RequestParam double height, @RequestParam double weight) {
        return bmiService.caculateBmi(height, weight);
    }

    @PostMapping("/bmi-post")
    public double caculateBmiPost(@RequestBody BmiRequest request) {
        return bmiService.caculateBmi(request.getHeight(), request.getWeight());
    }
}
