package com.example.baseballtalk.Service;

import com.example.baseballtalk.DTO.*;
import com.example.baseballtalk.Entity.PitcherEntity;
import com.example.baseballtalk.Entity.TeamEntity;
import com.example.baseballtalk.Repository.PitcherRepository;
import com.example.baseballtalk.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PitcherService {
    @Autowired
    private PitcherRepository repository;
    @Autowired
    private TeamRepository team_repository;

    public Page<PitcherResponseDTO> getAllPitcher(int pageNo) {
        int pageSize = 5;
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<PitcherEntity> pitcherAllPage = repository.findAll(pageRequest);
        List<PitcherResponseDTO> dtos = pitcherAllPage.getContent().stream()
                .map(entity -> new PitcherResponseDTO(
                        entity.getId(),
                        entity.getName(),
                        entity.getAge(),
                        entity.getHeight(),
                        entity.getWeight(),
                        entity.getImage(),
                        entity.getEra(),
                        entity.getGame(),
                        entity.getInning(),
                        entity.getWin(),
                        entity.getLose(),
                        entity.getSave(),
                        entity.getHold(),
                        new TeamSmallDTO(
                                entity.getTeam().getId(),
                                entity.getTeam().getTeamname(),
                                entity.getTeam().getTeamnameEn()
                        )))
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageRequest, pitcherAllPage.getTotalElements());
    }

    public Page<PitcherResponseDTO> getAllPitcherByEra(int pageNo) {
        int pageSize = 5;
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<PitcherEntity> pitcherbyEraPage = repository.findAllByOrderByEraAsc(pageRequest);
        List<PitcherResponseDTO> dtos = pitcherbyEraPage.getContent().stream()
                .map(entity -> new PitcherResponseDTO(
                        entity.getId(),
                        entity.getName(),
                        entity.getAge(),
                        entity.getHeight(),
                        entity.getWeight(),
                        entity.getImage(),
                        entity.getEra(),
                        entity.getGame(),
                        entity.getInning(),
                        entity.getWin(),
                        entity.getLose(),
                        entity.getSave(),
                        entity.getHold(),
                        new TeamSmallDTO(
                                entity.getTeam().getId(),
                                entity.getTeam().getTeamname(),
                                entity.getTeam().getTeamnameEn()
                        )))
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageRequest, pitcherbyEraPage.getTotalElements());
    }

    public PitcherResponseDTO getPitcher(int id) {
        if (repository.existsById(id)) {
            PitcherEntity pitcher = repository.findById(id);
            PitcherResponseDTO dto = new PitcherResponseDTO(
                    pitcher.getId(),
                    pitcher.getName(),
                    pitcher.getAge(),
                    pitcher.getHeight(),
                    pitcher.getWeight(),
                    pitcher.getImage(),
                    pitcher.getEra(),
                    pitcher.getGame(),
                    pitcher.getInning(),
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
        PitcherEntity entity;
        if (team_repository.existsById(dto.getTeam())) {
            TeamEntity team = team_repository.findById(dto.getTeam());

            if (repository.existsByNameAndTeam(dto.getName(), team)) {
                entity = repository.findByNameAndTeam(dto.getName(), team);
            } else {
                entity = new PitcherEntity();

                entity.setTeam(team);
            }

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

            repository.save(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team Not Found.");
        }

    }
}
