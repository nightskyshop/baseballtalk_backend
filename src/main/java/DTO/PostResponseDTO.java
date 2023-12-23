package DTO;

import com.example.test.Entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDTO {
    private String title;
    private String content;
    private int author;
}