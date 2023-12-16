package DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private String title;
    private String content;

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }
}