package com.sparta.developmentgroup1.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController { //임시 컨트롤러
    @GetMapping("/check") //체크
    public ResponseEntity check(){
        return ResponseEntity.ok().body("success");
    }
}