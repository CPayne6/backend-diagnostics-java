package com.example.prime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.prime.handlers.AtkinHandler;
import com.example.prime.handlers.EratosthenesHandler;
import com.example.prime.handlers.SundaramHandler;

@RestController
@RequestMapping("/prime")
@Validated
public class PrimeController {
    @Value("${upperLimit: #{1000000}}")
    private int UPPER_LIMIT;

    @Value("${lowerLimit:1}")
    private int LOWER_LIMIT;

    @GetMapping("/eratosthenes")
    public ResponseEntity<String> eratosthenes(
            @RequestParam(required = false, defaultValue = "${defaultUpperLimit:100}") Integer limit) {
        return new EratosthenesHandler(UPPER_LIMIT, LOWER_LIMIT).handler(limit);
    }

    @GetMapping("/sundaram")
    public ResponseEntity<String> sundaram(
            @RequestParam(required = false, defaultValue = "${defaultUpperLimit:100}") Integer limit) {
        return new SundaramHandler(UPPER_LIMIT, LOWER_LIMIT).handler(limit);
    }

    @GetMapping("/atkin")
    public ResponseEntity<String> atkin(
            @RequestParam(required = false, defaultValue = "${defaultUpperLimit:100}") Integer limit) {
        return new AtkinHandler(UPPER_LIMIT, LOWER_LIMIT).handler(limit);
    }

}