package com.example.test.Service;

import com.example.test.DTO.HitterDTO;
import com.example.test.DTO.HitterResponseDTO;
import com.example.test.DTO.TeamSmallDTO;
import com.example.test.Entity.HitterEntity;
import com.example.test.Entity.TeamEntity;
import com.example.test.Repository.HitterRepository;
import com.example.test.Repository.TeamRepository;
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
public class HitterService {
    @Autowired
    private HitterRepository repository;
    @Autowired
    private TeamRepository team_repository;

    public Page<HitterResponseDTO> getAllHitter(int pageNo) {
        int pageSize = 5;
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<HitterEntity> hitterAllPage = repository.findAll(pageRequest);
        List<HitterResponseDTO> dtos = hitterAllPage.getContent().stream()
                .map(entity -> new HitterResponseDTO(
                        entity.getId(),
                        entity.getName(),
                        entity.getAge(),
                        entity.getHeight(),
                        entity.getWeight(),
                        entity.getImage(),
                        entity.getAvg(),
                        entity.getSlg(),
                        entity.getObp(),
                        entity.getOps(),
                        entity.getGame(),
                        entity.getHit(),
                        entity.getSecondHit(),
                        entity.getThirdHit(),
                        entity.getHomeRun(),
                        entity.getRbi(),
                        new TeamSmallDTO(
                                entity.getTeam().getId(),
                                entity.getTeam().getTeamname(),
                                entity.getTeam().getTeamnameEn()
                        )))
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageRequest, hitterAllPage.getTotalElements());
    }

    public Page<HitterResponseDTO> getAllHitterByAvg(int pageNo) {
        int pageSize = 5;
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<HitterEntity> hitterbyAvgPage = repository.findAllByOrderByAvgDesc(pageRequest);
        List<HitterResponseDTO> dtos = hitterbyAvgPage.getContent().stream()
                .map(entity -> new HitterResponseDTO(
                        entity.getId(),
                        entity.getName(),
                        entity.getAge(),
                        entity.getHeight(),
                        entity.getWeight(),
                        entity.getImage(),
                        entity.getAvg(),
                        entity.getSlg(),
                        entity.getObp(),
                        entity.getOps(),
                        entity.getGame(),
                        entity.getHit(),
                        entity.getSecondHit(),
                        entity.getThirdHit(),
                        entity.getHomeRun(),
                        entity.getRbi(),
                        new TeamSmallDTO(
                                entity.getTeam().getId(),
                                entity.getTeam().getTeamname(),
                                entity.getTeam().getTeamnameEn()
                        )))
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageRequest, hitterbyAvgPage.getTotalElements());
    }

    public HitterResponseDTO getHitter(int id) {
        if (repository.existsById(id)) {
            HitterEntity hitter = repository.findById(id);
            HitterResponseDTO dto = new HitterResponseDTO(
                    hitter.getId(),
                    hitter.getName(),
                    hitter.getAge(),
                    hitter.getHeight(),
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
        HitterEntity entity;
        if (team_repository.existsById(dto.getTeam())) {
            TeamEntity team = team_repository.findById(dto.getTeam());

            if (repository.existsByNameAndTeam(dto.getName(), team)) {
                entity = repository.findByNameAndTeam(dto.getName(), team);
            } else {
                entity = new HitterEntity();

                entity.setTeam(team);
            }

            entity.setName(dto.getName());
            entity.setAge(dto.getAge());
            entity.setWeight(dto.getWeight());
            entity.setHeight(dto.getHeight());
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

            repository.save(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team Not Found.");
        }

    }
}
