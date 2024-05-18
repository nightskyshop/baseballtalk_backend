package com.example.test.Service;

import com.example.test.DTO.*;
import com.example.test.Entity.PitcherEntity;
import com.example.test.Entity.TeamEntity;
import com.example.test.Repository.PitcherRepository;
import com.example.test.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PitcherService {
    @Autowired
    private PitcherRepository repository;
    @Autowired
    private TeamRepository team_repository;

    public PitcherResponseDTO getPitcher(int id) {
        if (repository.existsById(id)) {
            PitcherEntity pitcher = repository.findById(id);
            PitcherResponseDTO dto = new PitcherResponseDTO(
                    pitcher.getId(),
                    pitcher.getName(),
                    pitcher.getAge(),
                    pitcher.getWeight(),
                    pitcher.getImage(),
                    pitcher.getEra(),
                    pitcher.getGame(),
                    pitcher.getWin(),
                    pitcher.getLose(),
                    pitcher.getSave(),
                    pitcher.getHold(),
                    new TeamSmallDTO(
                            pitcher.getTeam().getId(),
                            pitcher.getTeam().getTeamname(),
                            pitcher.getTeam().getTeamnameEn()
                    )
            );
            return dto;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player Not Found");
        }
    }

    public void createPitcher(PitcherDTO dto) {
        PitcherEntity entity = new PitcherEntity();
        if (team_repository.existsById(dto.getTeam())) {
            TeamEntity team = team_repository.findById(dto.getTeam());

            entity.setName(dto.getName());
            entity.setAge(dto.getAge());
            entity.setHeight(dto.getHeight());
            entity.setWeight(dto.getWeight());
            entity.setImage(dto.getImage());
            entity.setEra(dto.getEra());
            entity.setGame(dto.getGame());
            entity.setWin(dto.getWin());
            entity.setLose(dto.getLose());
            entity.setSave(dto.getSave());
            entity.setHold(dto.getHold());

            entity.setTeam(team);

            repository.save(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team Not Found.");
        }

    }
}
