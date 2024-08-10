package com.example.baseballtalk.Controller;

import com.example.baseballtalk.DTO.TeamDTO;
import com.example.baseballtalk.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService service;

    @GetMapping("")
    public List<TeamDTO> getAllTeam() { return service.getAllTeam(); }

    @GetMapping("/teamNameEn")
    public TeamDTO getTeamByEngName(@RequestParam String team_eng_name) { return service.getTeamByEngName(team_eng_name); };

    @GetMapping("/{team_id}")
    public TeamDTO getTeam(@PathVariable int team_id) { return service.getTeam(team_id); }

    @PostMapping("")
    public void createUpdateTeam(@RequestBody TeamDTO dto) { service.createUpdateTeam(dto); }
}
