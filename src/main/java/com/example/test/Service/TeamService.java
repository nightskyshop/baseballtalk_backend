package com.example.test.Service;

import com.example.test.DTO.TeamDTO;
import com.example.test.Entity.TeamEntity;
import com.example.test.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    @Autowired
    private TeamRepository repository;

    public void createTeam(TeamDTO dto) {
        TeamEntity entity = new TeamEntity();

        entity.setTeamname(dto.getTeamname());
        entity.setRank_num(dto.getRank_num());
        entity.setGame(dto.getGame());
        entity.setWin(dto.getWin());
        entity.setLose(dto.getLose());
        entity.setTie(dto.getTie());
        entity.setWinavg(dto.getWinavg());
        entity.setAvg(dto.getAvg());
        entity.setEra(dto.getEra());

        repository.save(entity);
    }
}
