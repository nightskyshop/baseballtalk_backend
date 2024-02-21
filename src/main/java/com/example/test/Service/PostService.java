package com.example.test.Service;

import com.example.test.DTO.PostDTO;
import com.example.test.DTO.PostResponseDTO;
import com.example.test.DTO.TeamSmallDTO;
import com.example.test.DTO.UserResponseDTO;
import com.example.test.Entity.PostEntity;
import com.example.test.Entity.TeamEntity;
import com.example.test.Entity.UserEntity;
import com.example.test.Repository.PostRepository;
import com.example.test.Repository.TeamRepository;
import com.example.test.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class PostService {
    @Autowired
    private PostRepository repository;
    @Autowired
    private UserRepository user_repository;
    @Autowired
    private TeamRepository team_repository;

    public Page<PostResponseDTO> getAllPost(int pageNo) {
        int pageSize = 10;
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<PostEntity> postAllPage = repository.findAllByOrderByCreatedAtDesc(pageRequest);
        List<PostResponseDTO> dtos = postAllPage.getContent().stream()
                .map(entity -> new PostResponseDTO(
                        entity.getId(),
                        entity.getTitle(),
                        entity.getContent(),
                        new TeamSmallDTO(
                                entity.getTeam().getId(),
                                entity.getTeam().getTeamname()
                        ),
                        entity.getCategory(),
                        UserResponseDTO.of(entity.getAuthor()),
                        entity.getLike().size(),
                        entity.getCreatedAt(),
                        entity.getUpdatedAt()))
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageRequest, postAllPage.getTotalElements());
    }

    public Page<PostResponseDTO> getPostbyUser(int pageNo, int user_id) {
        if (user_repository.existsById(user_id)) {
            UserEntity author = user_repository.findById(user_id);

            int pageSize = 10;
            PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
            Page<PostEntity> postbyUserPage = repository.findByAuthorOrderByCreatedAtDesc(pageRequest, author);
            List<PostResponseDTO> dtos = postbyUserPage.getContent().stream()
                    .map(entity -> new PostResponseDTO(
                            entity.getId(),
                            entity.getTitle(),
                            entity.getContent(),
                            new TeamSmallDTO(
                                    entity.getTeam().getId(),
                                    entity.getTeam().getTeamname()
                            ),
                            entity.getCategory(),
                            UserResponseDTO.of(entity.getAuthor()),
                            entity.getLike().size(),
                            entity.getCreatedAt(),
                            entity.getUpdatedAt()))
                    .collect(Collectors.toList());
            return new PageImpl<>(dtos, pageRequest, postbyUserPage.getTotalElements());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found.");
        }
    }

    public Page<PostResponseDTO> getPostbyTeam(int pageNo, int team_id) {
        if (team_repository.existsById(team_id)) {
            TeamEntity team = team_repository.findById(team_id);

            int pageSize = 10;
            PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
            Page<PostEntity> postbyTeamPage = repository.findByTeamOrderByCreatedAtDesc(pageRequest, team);
            List<PostResponseDTO> dtos = postbyTeamPage.getContent().stream()
                    .map(entity -> new PostResponseDTO(
                            entity.getId(),
                            entity.getTitle(),
                            entity.getContent(),
                            new TeamSmallDTO(
                                    entity.getTeam().getId(),
                                    entity.getTeam().getTeamname()
                            ),
                            entity.getCategory(),
                            UserResponseDTO.of(entity.getAuthor()),
                            entity.getLike().size(),
                            entity.getCreatedAt(),
                            entity.getUpdatedAt()))
                    .collect(Collectors.toList());
            return new PageImpl<>(dtos, pageRequest, postbyTeamPage.getTotalElements());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team Not Found.");
        }
    }

    public PostResponseDTO getPost(int id) {
        if (repository.existsById(id)) {
            PostEntity post = repository.findById(id);
            System.out.println("GETLIKE " + post.getLike().size());
            PostResponseDTO dto = new PostResponseDTO(
                    post.getId(),
                    post.getTitle(),
                    post.getContent(),
                    new TeamSmallDTO(
                            post.getTeam().getId(),
                            post.getTeam().getTeamname()
                    ),
                    post.getCategory(),
                    UserResponseDTO.of(post.getAuthor()),
                    post.getLike().size(),
                    post.getCreatedAt(),
                    post.getUpdatedAt()
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
