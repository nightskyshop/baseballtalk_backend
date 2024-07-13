package com.example.baseballtalk.Controller;

import com.example.baseballtalk.DTO.HitterDTO;
import com.example.baseballtalk.DTO.HitterResponseDTO;
import com.example.baseballtalk.Service.HitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hitter")
public class HitterController {
    @Autowired
    private HitterService service;

    @GetMapping("")
    public Page<HitterResponseDTO> getAllHitter(@RequestParam int pageNo) { return service.getAllHitter(pageNo); }

    @GetMapping("/avg")
    public Page<HitterResponseDTO> getAllHitterByAvg(@RequestParam int pageNo) { return  service.getAllHitterByAvg(pageNo); }

    @GetMapping("/{id}")
    public HitterResponseDTO getHitter(@PathVariable int id) { return service.getHitter(id); }

    @PostMapping("")
    public void createHitter(@RequestBody HitterDTO dto) { service.createHitter(dto); }
}
