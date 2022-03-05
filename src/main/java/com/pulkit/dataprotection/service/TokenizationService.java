package com.pulkit.dataprotection.service;

import com.pulkit.dataprotection.exception.DataProtectionException;
import com.pulkit.dataprotection.repository.TokenizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenizationService {

    @Autowired
    TokenizationRepository tokenizationRepository;

    public String tokenizeString(String inputString){

        Integer count = tokenizationRepository.getOriginalValueRecordCount(inputString);
        if(count == 1){
            return tokenizationRepository.getTokenizedValue(inputString);
        }
        else {
            Integer length = inputString.length();
            String tokenizedValue = randomStringGenerator(length);
            tokenizationRepository.insertRecord(inputString,tokenizedValue);
            return tokenizedValue;
        }

    }

    public String deTokenizeString(String inputString){

        Integer count = tokenizationRepository.getTokenizedValueRecordCount(inputString);
        if(count == 1){
            return tokenizationRepository.getOriginalValue(inputString);
        }
        else {
            throw new DataProtectionException("No Value Exist For Token : " + inputString);
        }
    }

    private String randomStringGenerator(Integer size){
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(size);

        for (int i = 0; i < size; i++) {

            int index = (int)(alphaNumericString.length() * Math.random());
            sb.append(alphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}
