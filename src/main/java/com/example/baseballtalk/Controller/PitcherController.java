package com.example.baseballtalk.Controller;

import com.example.baseballtalk.DTO.PitcherDTO;
import com.example.baseballtalk.DTO.PitcherResponseDTO;
import com.example.baseballtalk.Service.PitcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pitcher")
public class PitcherController {
    @Autowired
    private PitcherService service;

    @GetMapping("")
    public Page<PitcherResponseDTO> getAllPitcher(@RequestParam int pageNo) { return service.getAllPitcher(pageNo); };

    @GetMapping("/era")
    public Page<PitcherResponseDTO> getAllPitcherByEra(@RequestParam int pageNo) { return service.getAllPitcherByEra(pageNo); };

    @GetMapping("/{id}")
    public PitcherResponseDTO getPitcher(@PathVariable int id) { return service.getPitcher(id); }

    @PostMapping("")
    public void createPitcher(@RequestBody PitcherDTO dto) { service.createPitcher(dto); }
}
