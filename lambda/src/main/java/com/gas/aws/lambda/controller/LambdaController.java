package com.gas.aws.lambda.controller;

import com.gas.aws.lambda.service.LambdaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LambdaController {

    private final LambdaService lambdaService;

    public LambdaController(LambdaService lambdaService) {
        this.lambdaService = lambdaService;
    }

    @GetMapping("/invoke-lambda")
    public String invokeLambda(@RequestParam String functionName, @RequestParam String payload) {
        return lambdaService.invokeLambda(functionName, payload);
    }
}
