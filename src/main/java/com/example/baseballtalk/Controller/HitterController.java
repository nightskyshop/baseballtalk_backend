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
    public Page<HitterResponseDTO> getAllHitterByAvg(@RequestParam int pageNo, @RequestParam int pageSize) { return  service.getAllHitterByAvg(pageNo, pageSize); }

    @GetMapping("/{id}")
    public HitterResponseDTO getHitter(@PathVariable int id) { return service.getHitter(id); }

    @GetMapping("/random")
    public HitterResponseDTO getRandomHitter() { return service.getRandomHitter(); }

    @GetMapping("/random/{team_id}")
    public HitterResponseDTO getRandomHitterByTeam(@PathVariable int team_id) { return service.getRandomHitterByTeam(team_id); }

    @GetMapping("/search")
    public Page<HitterResponseDTO> searchHitter(@RequestParam int pageNo, @RequestParam String searchParam) { return service.searchHitter(pageNo, searchParam); }

    @PostMapping("")
    public void createHitter(@RequestBody HitterDTO dto, @RequestParam String flag) { service.createHitter(dto, flag); }

    @DeleteMapping("")
    public void deleteAllHitter() { service.deleteAllHitter(); };
}
