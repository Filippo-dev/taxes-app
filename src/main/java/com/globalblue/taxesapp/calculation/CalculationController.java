package com.globalblue.taxesapp.calculation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/calculations")
public class CalculationController {

    @GetMapping
    public String myApi(){
        return "Welcome to taxes app!";
    }
}
