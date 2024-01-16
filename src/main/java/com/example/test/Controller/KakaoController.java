package com.example.test.Controller;

import com.example.test.Entity.UserEntity;
import com.example.test.Service.KakaoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao")
public class KakaoController {
//    private final KakaoService kakaoService;
//
//    @GetMapping("/login")
//    public String login() {
//        return kakaoService.getKakaoLogin();
//    }
//
//    @GetMapping("/redirect")
//    public String redirect(HttpServletRequest request) throws Exception {
//        System.out.println("auth code:" + request.getParameter("code"));
//        String user = kakaoService.getKakaoInfo(request.getParameter("code"), request);
//        return "localhost:3000/user-profile/";
//    }
}
