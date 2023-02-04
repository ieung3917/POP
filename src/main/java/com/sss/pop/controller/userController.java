package com.sss.pop.controller;

import com.sss.pop.dao.userDAO;
import com.sss.pop.dto.userDTO;
import com.sss.pop.service.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class userController {
    
    private ModelAndView mav = new ModelAndView();

    private final userService usersvc;

    private final userDAO userdao;

    
    
    // userJoinForm : 회원가입 페이지로 이동
    @GetMapping("/userJoinForm")
    public String userJoinForm() {
        return "userJoinForm";
    }

    // idOverlapCheck : 아이디 중복 체크(ajax)
    @PostMapping("/idOverlapCheck")
    public @ResponseBody String idOverlapCheck(@RequestParam("userId") String userId) {

        String result = usersvc.idOverlapCheck(userId);

        return result;
    }

    // userEmailCheck : 이메일 인증 번호 발송
    @PostMapping("/userEmailCheck")
    public @ResponseBody String userEmailCheck(@RequestParam("userEmail") String userEmail) {

        String uuid = usersvc.userEmailCheck(userEmail);

        return uuid;
    }

    // userJoin : 회원가입 처리
    @PostMapping("userJoin")
    public ModelAndView userJoin(@ModelAttribute userDTO user) throws IOException {

        mav = usersvc.userJoin(user);

        return mav;
    }
    
    // userLoginForm : 로그인 페이지 요청
    @GetMapping("/userLoginForm")
    public String userLoginForm() {
        return "userLoginForm";
    }

    // userLogin : 로그인 처리
    @PostMapping("/userLogin")
    public @ResponseBody int userLogin(@ModelAttribute userDTO user
            , HttpServletRequest request
            , HttpServletResponse response
            , @RequestParam(value = "remember", required = false, defaultValue = "") String remember) {



//        userDTO login = usersvc.userLogin(user);

        int result = usersvc.userLogin(user);

        // 로그인 저장 체크박스를 체크하였을 경우 아이디 쿠키 생성
        if(result>0) {
            if (remember.equals("createCookie")) {
                //쿠키에 시간 정보를 주지 않으면 세션 쿠키가 된다. (브라우저 종료시 모두 종료)
                Cookie idCookie = new Cookie("idCookie", user.getUserId());
                idCookie.setMaxAge(60 * 60 * 24 * 30);
                response.addCookie(idCookie);
            }
        }

        // 로그인 성공 처리
        return result;
    }

    // userLogout : 로그아웃(아이디 쿠키, 세션 모두 삭재)
    @GetMapping("/userLogout")
    public String userLogout(HttpSession session, HttpServletResponse response) {

            Cookie logoutCookie = new Cookie("idCookie", null); // "(쿠키 이름)"에 대한 값을 null로 지정

            logoutCookie.setMaxAge(0);          // 유효시간을 0으로 설정
            response.addCookie(logoutCookie);   // 응답 헤더에 추가해서 없어지도록 함


        if(session != null) {
            session.invalidate();
        }

        return "redirect:/index";
    }

    // userLoginForm : 로그인 페이지 요청
    @GetMapping("/userModicheck")
    public String userModicheck() {
        return "userModicheck";
    }

    @GetMapping("/userModiForm")
    public ModelAndView userModiForm(@RequestParam("userId") String userId) {

        mav = usersvc.userModiForm(userId);

        return mav;
    }

    @PostMapping("/userModicheck")
    public @ResponseBody int userModicheck(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw) {

        System.out.println(userId.getClass().getName());

        int result = usersvc.userModicheck(userId,userPw);

        return result;
    }

    @PostMapping("/userModi")
    public ModelAndView userModi(@ModelAttribute userDTO user) throws IOException {


        mav = usersvc.userModi(user);

        return mav;
    }


    @PostMapping("/cashRecharge")
    public @ResponseBody int cashRecharge(@ModelAttribute userDTO user) {

         userdao.cashRecharge(user);

        return 0;
    }


    ////////////////////////////////////////////////////////////////////////////////
    // userMyPage : 내 상점 페이지 요청
    @GetMapping("/userStore")
    public ModelAndView userMyPage(@RequestParam("userId") String userId) {

        mav = usersvc.userStore(userId);

        return mav;
    }





    
    
    
}
