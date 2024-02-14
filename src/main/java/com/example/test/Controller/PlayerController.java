package com.example.test.Controller;

import com.example.test.DTO.PlayerDTO;
import com.example.test.DTO.PlayerResponseDTO;
import com.example.test.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService service;

    @GetMapping("/{id}")
    public PlayerResponseDTO getPlayer(@PathVariable int id) { return service.getPlayer(id); }

    @PostMapping("")
    public void createPlayer(@RequestBody PlayerDTO dto) { service.createPlayer(dto); }
}
