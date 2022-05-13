package com.globalblue.taxesapp.calculation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/calculations")
public class CalculationController {

    private final CalculationService calculationService;

    @Autowired
    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping
    public String myApi(){
        return "Welcome to taxes app!";
    }

    @GetMapping(path = "/getFromNet")
    public Calculation getFromNet(@RequestParam("net") String net, @RequestParam("vatRate") String vatRate){
        return calculationService.getFromNet(net,vatRate);
    }
}
