package com.pulkit.dataprotection.controller;

import com.pulkit.dataprotection.service.TokenizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin("*")
public class TokenizationController {

    @Autowired
    TokenizationService tokenizationService;

    @GetMapping("/tokenize/{inputString}")
    public String tokenize(@PathVariable String inputString){
        return tokenizationService.tokenizeString(inputString);
    }

    @GetMapping("/deTokenize/{inputString}")
    public String deTokenize(@PathVariable String inputString){
        return tokenizationService.deTokenizeString(inputString);
    }
}
