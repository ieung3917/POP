package com.sss.pop.kakaoLogin;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kakaoLogin")
public class kakaoLoginController {

    private final kakaoLoginService klsvc;

    ModelAndView mav = new ModelAndView();

    @GetMapping("/callback")
    public ModelAndView kakaoLogin(String code) throws Exception {
        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        String token = klsvc.getAccessToken(code);

        // 가져온 토큰으로 회원 정보 조회
        mav = klsvc.createKakaoUser(token);

        return mav;
    }



}
