package com.example.test.Service;

import com.example.test.DTO.PlayerDTO;
import com.example.test.DTO.PlayerResponseDTO;
import com.example.test.DTO.TeamSmallDTO;
import com.example.test.Entity.PlayerEntity;
import com.example.test.Entity.TeamEntity;
import com.example.test.Repository.PlayerRepository;
import com.example.test.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository repository;
    @Autowired
    private TeamRepository team_repository;

    public PlayerResponseDTO getPlayer(int id) {
        if (repository.existsById(id)) {
            PlayerEntity player = repository.findById(id);
            PlayerResponseDTO dto = new PlayerResponseDTO(
                    player.getId(),
                    player.getName(),
                    player.getType(),
                    player.getAge(),
                    player.getWeight(),
                    player.getImage(),
                    new TeamSmallDTO(
                            player.getTeam().getId(),
                            player.getTeam().getTeamname(),
                            player.getTeam().getTeamnameEn()
                    )
            );
            return dto;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player Not Found");
        }
    }

    public void createPlayer(PlayerDTO dto) {
        PlayerEntity entity = new PlayerEntity();
        if (team_repository.existsById(dto.getTeam())) {
            TeamEntity team = team_repository.findById(dto.getTeam());

            entity.setName(dto.getName());
            entity.setType(dto.getType());
            entity.setAge(dto.getAge());
            entity.setHeight(dto.getHeight());
            entity.setWeight(dto.getWeight());
            entity.setImage(dto.getImage());
            entity.setTeam(team);

            repository.save(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team Not Found.");
        }

    }
}
