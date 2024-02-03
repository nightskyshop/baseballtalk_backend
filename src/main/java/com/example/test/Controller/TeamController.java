package com.example.test.Controller;

import com.example.test.DTO.TeamDTO;
import com.example.test.Service.TeamService;
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

    @GetMapping("/{team_id}")
    public TeamDTO getTeam(@PathVariable int team_id) { return service.getTeam(team_id); }

    @PostMapping("")
    public void createTeam(@RequestBody TeamDTO dto) { service.createTeam(dto); }
}
