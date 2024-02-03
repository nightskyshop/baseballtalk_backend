package com.example.test.Controller;

import com.example.test.DTO.UserDTO;
import com.example.test.DTO.UserResponseDTO;
import com.example.test.Entity.UserEntity;
import com.example.test.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable int id) { return service.getUser(id); }

    @PatchMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestBody UserDTO dto) { service.updateUser(id, dto); }

    @PatchMapping("/{id}/password")
    public void updatePassword(@PathVariable int id, @RequestBody UserDTO dto) { service.updatePassword(id, dto); }
}
