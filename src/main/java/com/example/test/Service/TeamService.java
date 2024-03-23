package com.example.test.Service;

import com.example.test.DTO.TeamDTO;
import com.example.test.Entity.TeamEntity;
import com.example.test.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {
    @Autowired
    private TeamRepository repository;

    public List<TeamDTO> getAllTeam() {
        List<TeamEntity> teamAll = repository.findAllByOrderByRanknumAsc();
        List<TeamDTO> teamAllDTOList = teamAll.stream()
                .map(entity -> new TeamDTO(
                        entity.getId(),
                        entity.getTeamname(),
                        entity.getTeamnameEn(),
                        entity.getRanknum(),
                        entity.getGame(),
                        entity.getWin(),
                        entity.getLose(),
                        entity.getTie(),
                        entity.getWinavg(),
                        entity.getAvg(),
                        entity.getEra()))
                .collect(Collectors.toList());
        return teamAllDTOList;
    }

    public TeamDTO getTeam(int team_id) {
        TeamEntity team = repository.findById(team_id);

        return new TeamDTO(
                team.getId(),
                team.getTeamname(),
                team.getTeamnameEn(),
                team.getRanknum(),
                team.getGame(),
                team.getWin(),
                team.getLose(),
                team.getTie(),
                team.getWinavg(),
                team.getAvg(),
                team.getEra()
        );
    }

    public void createUpdateTeam(TeamDTO dto) {
        TeamEntity entity;
        if (repository.existsByTeamname(dto.getTeamname())) {
            entity = repository.findByTeamname(dto.getTeamname());

            entity.setTeamname(dto.getTeamname());
            entity.setRanknum(dto.getRanknum());
            entity.setGame(dto.getGame());
            entity.setWin(dto.getWin());
            entity.setLose(dto.getLose());
            entity.setTie(dto.getTie());
            entity.setWinavg(dto.getWinavg());
            entity.setAvg(dto.getAvg());
            entity.setEra(dto.getEra());
        } else {
            entity = new TeamEntity();

            entity.setTeamname(dto.getTeamname());
            entity.setRanknum(dto.getRanknum());
            entity.setGame(dto.getGame());
            entity.setWin(dto.getWin());
            entity.setLose(dto.getLose());
            entity.setTie(dto.getTie());
            entity.setWinavg(dto.getWinavg());
            entity.setAvg(dto.getAvg());
            entity.setEra(dto.getEra());
        }

        repository.save(entity);
    }
}
