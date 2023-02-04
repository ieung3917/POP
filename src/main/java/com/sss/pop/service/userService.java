package com.sss.pop.service;

import com.sss.pop.dto.userDTO;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

public interface userService {
    
    // idOverlapCheck : 아이디 중복 체크
    String idOverlapCheck(String userId);

    // userEmailCheck : 이메일 인증 번호 발송
    String userEmailCheck(String userEmail);

    // userJoin : 회원가입 처리
    ModelAndView userJoin(userDTO user) throws IOException;

    // userLogin : 로그인(쿠키 생성)
    int userLogin(userDTO user);

    // userStore : 회원 상점으로 이동
    ModelAndView userStore(String userId);

    ModelAndView userModiForm(String userId);

    int userModicheck(String userId, String userPw);

    ModelAndView userModi(userDTO user) throws IOException;
}
