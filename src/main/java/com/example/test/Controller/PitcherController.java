package com.example.test.Controller;

import com.example.test.DTO.HitterDTO;
import com.example.test.DTO.HitterResponseDTO;
import com.example.test.DTO.PitcherDTO;
import com.example.test.DTO.PitcherResponseDTO;
import com.example.test.Service.PitcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pitcher")
public class PitcherController {
    @Autowired
    private PitcherService service;

    @GetMapping("/{id}")
    public PitcherResponseDTO getPitcher(@PathVariable int id) { return service.getPitcher(id); }

    @PostMapping("")
    public void createPitcher(@RequestBody PitcherDTO dto) { service.createPitcher(dto); }
}
