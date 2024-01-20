package com.example.test.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoDTO {
    private String email;
    private String nickname;
    private String profileImgUrl;

    public KakaoDTO(String email, String nickname, String profileImgUrl) {
        this.email = email;
        this.nickname = nickname;
        this.profileImgUrl = profileImgUrl;
    }
}
