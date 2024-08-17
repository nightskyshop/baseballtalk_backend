package com.example.baseballtalk.Service;

import com.example.baseballtalk.DTO.*;
import com.example.baseballtalk.Entity.HitterEntity;
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Random;
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
                        entity.getWhip(),
                        new TeamSmallDTO(
                                entity.getTeam().getId(),
                                entity.getTeam().getTeamname(),
                                entity.getTeam().getTeamnameEn()
                        )))
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageRequest, pitcherAllPage.getTotalElements());
    }

    public Page<PitcherResponseDTO> getAllPitcherByEra(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<PitcherEntity> pitcherbyEraPage = repository.findAllByRankedOrderByEraAsc(true, pageRequest);
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
                        entity.getWhip(),
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
                    pitcher.getWhip(),
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

    public PitcherResponseDTO getRandomPitcher() {
        List<PitcherEntity> pitchers = repository.findAll();
        Random random = new Random();
        PitcherEntity pitcher = pitchers.get(random.nextInt(pitchers.size()));

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
                pitcher.getWhip(),
                new TeamSmallDTO(
                        pitcher.getTeam().getId(),
                        pitcher.getTeam().getTeamname(),
                        pitcher.getTeam().getTeamnameEn()
                )
        );
        return dto;
    }

    public PitcherResponseDTO getRandomPitcherByTeam(int team_id) {
        if (team_repository.existsById(team_id)) {
            TeamEntity team = team_repository.findById(team_id);
            List<PitcherEntity> pitchers = repository.findAllByTeam(team);
            Random random = new Random();
            PitcherEntity pitcher = pitchers.get(random.nextInt(pitchers.size()));

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
                    pitcher.getWhip(),
                    new TeamSmallDTO(
                            pitcher.getTeam().getId(),
                            pitcher.getTeam().getTeamname(),
                            pitcher.getTeam().getTeamnameEn()
                    )
            );
            return dto;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team Not Found");
        }
    }


    public Page<PitcherResponseDTO> searchPitcher(int pageNo, String searchParam) {
        int pageSize = 5;
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<PitcherEntity> pitcherbyEraPage = repository.findAllByNameContainingOrderByGameDesc(searchParam, pageRequest);
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
                        entity.getWhip(),
                        new TeamSmallDTO(
                                entity.getTeam().getId(),
                                entity.getTeam().getTeamname(),
                                entity.getTeam().getTeamnameEn()
                        )))
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageRequest, pitcherbyEraPage.getTotalElements());
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
                entity.setRanked(dto.isRanked());
            }

            entity.setName(dto.getName());
            entity.setAge(dto.getAge());
            entity.setHeight(dto.getHeight());
            entity.setWeight(dto.getWeight());
            entity.setImage(dto.getImage());
            entity.setEra(dto.getEra());
            entity.setGame(dto.getGame());
            entity.setInning(dto.getInning());
            entity.setWin(dto.getWin());
            entity.setLose(dto.getLose());
            entity.setSave(dto.getSave());
            entity.setHold(dto.getHold());
            entity.setWhip(dto.getWhip());

            repository.save(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team Not Found.");
        }
    }


    static final String DB_URL = "jdbc:mysql://localhost:3306/baseballtalk";
    static final String USER = "root";
    static final String PASS = "javamysql1010*";

    public void deleteAllPitcher() {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String sql = "TRUNCATE TABLE pitcher_entity";
            stmt.executeUpdate(sql);
            System.out.println("Table deleted in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
