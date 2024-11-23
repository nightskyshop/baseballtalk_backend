package com.example.baseballtalk.Controller;

import com.example.baseballtalk.DTO.LoginDTO;
import com.example.baseballtalk.DTO.TokenDTO;
import com.example.baseballtalk.DTO.UserRequestDTO;
import com.example.baseballtalk.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public void signup(@RequestBody UserRequestDTO user) { service.signup(user); }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
        System.out.println(loginDTO.getEmail());
        System.out.println(loginDTO.getPassword());
        return ResponseEntity.ok(service.login(loginDTO));
    }

    @GetMapping("/refreshToken")
    public TokenDTO accessTokenFromRefreshToken(@RequestHeader("RefreshToken") String refreshToken) {
        return new TokenDTO();
    }
}
