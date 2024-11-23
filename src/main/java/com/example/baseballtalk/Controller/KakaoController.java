package com.example.baseballtalk.Controller;


import com.example.baseballtalk.DTO.TokenDTO;
import com.example.baseballtalk.Service.KakaoService;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao")
public class KakaoController {
//
//    @Autowired
//    private ReactiveClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    private KakaoService service;

    @Value("${frontend.url}")
    private String FRONTEND_URL;

    @GetMapping("/loginFailure")
    public String fail(){
        return "LOGIN FAIL";
    }

    @GetMapping("/redirect")
    public RedirectView redirect(@RequestParam String code) throws Exception {
        System.out.println(code);

        RedirectView redirectView = new RedirectView();

        TokenDTO tokenDTO;
        try {
            tokenDTO = service.getKakaoInfo(code);
        } catch (Exception err) {
            System.out.println();
            throw err;
        }

        String redirectUrl = FRONTEND_URL + "/kakao/redirect#" +
                "access_token=" + tokenDTO.getAccessToken() +
                "&refresh_token=" + tokenDTO.getRefreshToken() +
                "&token_expires_in=" + tokenDTO.getTokenExpiresIn() +
                "&refresh_token_expires_in=" + tokenDTO.getRefreshTokenExpiresIn();

        redirectView.setUrl(redirectUrl);

        // 클라이언트로 Fragment 포함된 URL로 리디렉션
        return redirectView;
    }
}
