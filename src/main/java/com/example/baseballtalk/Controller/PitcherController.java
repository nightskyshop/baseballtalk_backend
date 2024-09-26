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
    public Page<PitcherResponseDTO> getAllPitcherByEra(@RequestParam int pageNo, @RequestParam int pageSize) { return service.getAllPitcherByEra(pageNo, pageSize); };

    @GetMapping("/{id}")
    public PitcherResponseDTO getPitcher(@PathVariable int id) { return service.getPitcher(id); }

    @GetMapping("/random")
    public PitcherResponseDTO getRandomPitcher() { return service.getRandomPitcher(); }

    @GetMapping("/random/{team_id}")
    public PitcherResponseDTO getRandomPitcherByTeam(@PathVariable int team_id) { return service.getRandomPitcherByTeam(team_id); }

    @GetMapping("/search")
    public Page<PitcherResponseDTO> searchPitcher(@RequestParam int pageNo, @RequestParam String searchParam) { return service.searchPitcher(pageNo, searchParam); }

    @PostMapping("")
    public void createPitcher(@RequestBody PitcherDTO dto, @RequestParam String flag) { service.createPitcher(dto, flag); }

    @DeleteMapping("")
    public void deletePitcher() { service.deleteAllPitcher(); };
}
