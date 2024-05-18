package com.example.test.Service;

import com.example.test.DTO.HitterDTO;
import com.example.test.DTO.HitterResponseDTO;
import com.example.test.DTO.TeamSmallDTO;
import com.example.test.Entity.HitterEntity;
import com.example.test.Entity.TeamEntity;
import com.example.test.Repository.HitterRepository;
import com.example.test.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class HitterService {
    @Autowired
    private HitterRepository repository;
    @Autowired
    private TeamRepository team_repository;

    public HitterResponseDTO getHitter(int id) {
        if (repository.existsById(id)) {
            HitterEntity hitter = repository.findById(id);
            HitterResponseDTO dto = new HitterResponseDTO(
                    hitter.getId(),
                    hitter.getName(),
                    hitter.getAge(),
                    hitter.getWeight(),
                    hitter.getImage(),
                    hitter.getAvg(),
                    hitter.getSlg(),
                    hitter.getObp(),
                    hitter.getOps(),
                    hitter.getGame(),
                    hitter.getHit(),
                    hitter.getSecondHit(),
                    hitter.getThirdHit(),
                    hitter.getHomeRun(),
                    hitter.getRbi(),
                    new TeamSmallDTO(
                            hitter.getTeam().getId(),
                            hitter.getTeam().getTeamname(),
                            hitter.getTeam().getTeamnameEn()
                    )
            );
            return dto;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player Not Found");
        }
    }

    public void createHitter(HitterDTO dto) {
        HitterEntity entity = new HitterEntity();
        if (team_repository.existsById(dto.getTeam())) {
            TeamEntity team = team_repository.findById(dto.getTeam());


            entity.setName(dto.getName());
            entity.setAge(dto.getAge());
            entity.setWeight(dto.getWeight());
            entity.setImage(dto.getImage());
            entity.setAvg(dto.getAvg());
            entity.setSlg(dto.getSlg());
            entity.setObp(dto.getObp());
            entity.setOps(dto.getOps());
            entity.setGame(dto.getGame());
            entity.setHit(dto.getHit());
            entity.setSecondHit(dto.getSecondHit());
            entity.setThirdHit(dto.getThirdHit());
            entity.setHomeRun(dto.getHomeRun());
            entity.setRbi(dto.getRbi());
            entity.setTeam(team);

            repository.save(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team Not Found.");
        }

    }
}
