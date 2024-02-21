package com.example.test.Controller;

import com.example.test.DTO.LoginDTO;
import com.example.test.DTO.TokenDTO;
import com.example.test.DTO.UserDTO;
import com.example.test.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

//    @GetMapping("")
//    public String Root(){
//        return "abc.html";
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void signup(@RequestBody UserDTO user) { service.signup(user); }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
        System.out.println("dd");
        System.out.println(loginDTO.getEmail());
        System.out.println(loginDTO.getPassword());
        return ResponseEntity.ok(service.login(loginDTO));
    }
}
