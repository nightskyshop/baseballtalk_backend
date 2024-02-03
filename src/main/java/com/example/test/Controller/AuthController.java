package com.example.test.Controller;

import com.example.test.DTO.UserDTO;
import com.example.test.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/signup")
    public void signup(@RequestBody UserDTO user) { service.signup(user); }
}
