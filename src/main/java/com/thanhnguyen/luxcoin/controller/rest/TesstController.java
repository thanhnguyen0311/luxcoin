package com.thanhnguyen.luxcoin.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3")
public class TesstController {
    @GetMapping("/test")
    public ResponseEntity<?> checkUsername() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
