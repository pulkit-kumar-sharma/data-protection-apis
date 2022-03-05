package com.pulkit.dataprotection.controller;

import com.pulkit.dataprotection.exception.DataProtectionException;
import com.pulkit.dataprotection.service.TokenizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin("*")
@Slf4j
public class TokenizationController {

    @Autowired
    TokenizationService tokenizationService;

    @GetMapping("/tokenize/{inputString}")
    public ResponseEntity<Object> tokenize(@PathVariable String inputString){
        return ResponseEntity.ok(tokenizationService.tokenizeString(inputString));
    }

    @GetMapping("/deTokenize/{inputString}")
    public ResponseEntity<Object> deTokenize(@PathVariable String inputString){
        try {
            return ResponseEntity.ok(tokenizationService.deTokenizeString(inputString));
        }
        catch (DataProtectionException e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
