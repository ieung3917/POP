package com.sss.pop.kakaoLogin;

import org.springframework.web.servlet.ModelAndView;

public interface kakaoLoginService {

    // 카카오 인가 코드 전달
    String getAccessToken(String code);

    // 카카오 회원 정보 조회
    ModelAndView createKakaoUser(String token) throws Exception;
}
