package com.example.baseballtalk.Controller;


import com.example.baseballtalk.DTO.TokenDTO;
import com.example.baseballtalk.Service.KakaoService;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class KakaoController {
//
//    @Autowired
//    private ReactiveClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    private KakaoService service;

    @GetMapping("/loginFailure")
    public String fail(){
        return "LOGIN FAIL";
    }
    @GetMapping("/kakao")
    public TokenDTO redirect(@RequestParam String code) {
        try {
            return service.getKakaoInfo(code);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 실패");
        }
    }
}
