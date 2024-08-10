package com.example.baseballtalk.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class KakaoController {
//
//    @Autowired
//    private ReactiveClientRegistrationRepository clientRegistrationRepository;

    @GetMapping("/loginFailure")
    public String fail(){
        return "LOGIN FAIL";
    }
    @GetMapping("/kakao")
    public void redirect() {
        RedirectView redirectView = new RedirectView();
        //redirectView.setUrl("https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=b36ab199108d218efc3931bbf61e81ec&scope=profile_image%20profile_nickname%20account_email&redirect_uri=http://localhost:8080/login/oauth2/code/kakao&state=fjfjfjfjfj");

    }
}
