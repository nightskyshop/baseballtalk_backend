package com.example.baseballtalk.Service;

import com.example.baseballtalk.DTO.PostDTO;
import com.example.baseballtalk.DTO.PostResponseDTO;
import com.example.baseballtalk.DTO.TeamSmallDTO;
import com.example.baseballtalk.DTO.UserResponseDTO;
import com.example.baseballtalk.Entity.*;
import com.example.baseballtalk.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class PostService {
    @Autowired
    private PostRepository repository;
    @Autowired
    private UserRepository user_repository;
    @Autowired
    private TeamRepository team_repository;
    @Autowired
    private HitterRepository hitter_repository;
    @Autowired
    private HitterDataRepository hitter_data_repository;
    @Autowired
    private PitcherRepository pitcher_repository;
    @Autowired
    private PitcherDataRepository pitcher_data_repository;

    public Page<PostResponseDTO> getAllPost(int pageNo) {
        int pageSize = 5;
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<PostEntity> postAllPage = repository.findAllByOrderByCreatedAtDesc(pageRequest);
        List<PostResponseDTO> dtos = postAllPage.getContent().stream()
                .map(entity -> new PostResponseDTO(
                        entity.getId(),
                        entity.getTitle(),
                        entity.getContent(),
                        new TeamSmallDTO(
                                entity.getTeam().getId(),
                                entity.getTeam().getTeamname(),
                                entity.getTeam().getTeamnameEn()
                        ),
                        entity.getCategory(),
                        UserResponseDTO.of(entity.getAuthor()),
                        entity.getLike().size(),
                        entity.getCreatedAt(),
                        entity.getUpdatedAt(),
                        entity.getHitter(),
                        entity.getPitcher()))
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageRequest, postAllPage.getTotalElements());
    }

    public Page<PostResponseDTO> getPostbyUser(int pageNo, int user_id) {
        if (user_repository.existsById(user_id)) {
            UserEntity author = user_repository.findById(user_id);

            int pageSize = 5;
            PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
            Page<PostEntity> postbyUserPage = repository.findByAuthorOrderByCreatedAtDesc(pageRequest, author);
            List<PostResponseDTO> dtos = postbyUserPage.getContent().stream()
                    .map(entity -> new PostResponseDTO(
                            entity.getId(),
                            entity.getTitle(),
                            entity.getContent(),
                            new TeamSmallDTO(
                                    entity.getTeam().getId(),
                                    entity.getTeam().getTeamname(),
                                    entity.getTeam().getTeamnameEn()
                            ),
                            entity.getCategory(),
                            UserResponseDTO.of(entity.getAuthor()),
                            entity.getLike().size(),
                            entity.getCreatedAt(),
                            entity.getUpdatedAt(),
                            entity.getHitter(),
                            entity.getPitcher()))
                    .collect(Collectors.toList());
            return new PageImpl<>(dtos, pageRequest, postbyUserPage.getTotalElements());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found.");
        }
    }

    public Page<PostResponseDTO> getPostbyTeam(int pageNo, int team_id) {
        if (team_repository.existsById(team_id)) {
            TeamEntity team = team_repository.findById(team_id);

            int pageSize = 5;
            PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
            Page<PostEntity> postbyTeamPage = repository.findByTeamOrderByCreatedAtDesc(pageRequest, team);
            List<PostResponseDTO> dtos = postbyTeamPage.getContent().stream()
                    .map(entity -> new PostResponseDTO(
                            entity.getId(),
                            entity.getTitle(),
                            entity.getContent(),
                            new TeamSmallDTO(
                                    entity.getTeam().getId(),
                                    entity.getTeam().getTeamname(),
                                    entity.getTeam().getTeamnameEn()
                            ),
                            entity.getCategory(),
                            UserResponseDTO.of(entity.getAuthor()),
                            entity.getLike().size(),
                            entity.getCreatedAt(),
                            entity.getUpdatedAt(),
                            entity.getHitter(),
                            entity.getPitcher()))
                    .collect(Collectors.toList());
            return new PageImpl<>(dtos, pageRequest, postbyTeamPage.getTotalElements());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team Not Found.");
        }
    }

    public PostResponseDTO getPost(int id) {
        if (repository.existsById(id)) {
            PostEntity post = repository.findById(id);
            PostResponseDTO dto = new PostResponseDTO(
                    post.getId(),
                    post.getTitle(),
                    post.getContent(),
                    new TeamSmallDTO(
                            post.getTeam().getId(),
                            post.getTeam().getTeamname(),
                            post.getTeam().getTeamnameEn()
                    ),
                    post.getCategory(),
                    UserResponseDTO.of(post.getAuthor()),
                    post.getLike().size(),
                    post.getCreatedAt(),
                    post.getUpdatedAt(),
                    post.getHitter(),
                    post.getPitcher()
            );
            return dto;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post Not Found.");
        }
    }

    public void createPost(PostDTO dto) {
        PostEntity entity = new PostEntity();
        if (team_repository.existsById(dto.getTeam())) {
            TeamEntity team = team_repository.findById(dto.getTeam());

            entity.setContent(dto.getContent());
            entity.setTitle(dto.getTitle());
            entity.setTeam(team);
            entity.setCategory(dto.getCategory());

            UserEntity user = user_repository.findById(dto.getAuthor());
            entity.setAuthor(user);

            repository.save(entity);

            for (int i : dto.getHitterList()) {
                if (hitter_repository.existsById(i)) {
                    System.out.println("HITTER");
                    HitterEntity hitter = hitter_repository.findById(i);
                    HitterDataEntity hitterData = new HitterDataEntity();

                    hitterData.setName(hitter.getName());
                    hitterData.setImage(hitter.getImage());
                    hitterData.setAvg(hitter.getAvg());
                    hitterData.setHit(hitter.getHit());
                    hitterData.setHomeRun(hitter.getHomeRun());
                    hitterData.setRbi(hitter.getRbi());
                    hitterData.setStolenBase(hitter.getStolenBase());
                    hitterData.setPost(entity);

                    hitter_data_repository.save(hitterData);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hitter Not Found.");
                }
            }

            for (int i : dto.getPitcherList()) {
                System.out.println("PITCHER");
                if (pitcher_repository.existsById(i)) {
                    PitcherEntity pitcher = pitcher_repository.findById(i);
                    PitcherDataEntity pitcherData = new PitcherDataEntity();

                    pitcherData.setName(pitcher.getName());
                    pitcherData.setImage(pitcher.getImage());
                    pitcherData.setEra(pitcher.getEra());
                    pitcherData.setInning(pitcher.getInning());
                    pitcherData.setWin(pitcher.getWin());
                    pitcherData.setWhip(pitcher.getWhip());

                    pitcher_data_repository.save(pitcherData);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pitcher Not Found.");
                }
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team Not Found.");
        }
    }

    public void updatePost(int id, PostDTO dto) {
        if (repository.existsById(id)) {
            PostEntity entity = repository.findById(id);
            TeamEntity team = team_repository.findById(dto.getTeam());

            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());
            entity.setTeam(team);
            entity.setCategory(dto.getCategory());
            repository.save(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post Not Found.");
        }
    }

    @Transactional
    public void deletePost(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post Not Found.");
        }
    }
}
