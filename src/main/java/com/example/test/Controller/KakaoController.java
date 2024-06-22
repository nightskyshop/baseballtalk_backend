//package com.example.test.Controller;
//
//import com.example.test.DTO.TokenDTO;
//import com.example.test.Entity.UserEntity;
//import com.example.test.Service.KakaoService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/kakao")
//public class KakaoController {
//    private final KakaoService kakaoService;
//
//    @GetMapping("/{code}")
//    public TokenDTO redirect(@PathVariable String code) throws Exception {
//        System.out.println("auth code:" + code);
//        return kakaoService.getKakaoInfo(code);
//    }
//}
