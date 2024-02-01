package com.example.test.Controller;

import com.example.test.DTO.TeamDTO;
import com.example.test.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService service;

    @PostMapping("")
    public void createTeam(@RequestBody TeamDTO dto) { service.createTeam(dto); }
}
