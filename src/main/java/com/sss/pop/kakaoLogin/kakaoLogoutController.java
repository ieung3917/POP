package com.sss.pop.kakaoLogin;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kakaoLogout")
public class kakaoLogoutController {

    private ModelAndView mav = new ModelAndView();

    private final kakaoLogoutService klosvc;

    @GetMapping("/callbackLogout")
    public ModelAndView kakaoLogout() throws Exception {
//        System.out.println("로그아웃 컨트롤러 도착1");
//        String token = klosvc.getLogoutAccessToken();
//        System.out.println("로그아웃 controller 토큰 : "+token);

        klosvc.kakaoLogout();

        mav.setViewName("redirect:/");
        return mav;
    }



}
