package com.sss.pop.chat;

import com.sss.pop.auc.AuctionRoomRepository;
import com.sss.pop.auc.auDAO;
import com.sss.pop.dao.itemDAO;
import com.sss.pop.dao.mainDAO;
import com.sss.pop.dao.userDAO;
import com.sss.pop.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class controller {

    private final userDAO udao;

    private final AuctionRoomRepository repository;

    private final auDAO audao;

    private final itemDAO itemdao;

    private final mainDAO maindao;

    private ModelAndView mav;


    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/kakaoPay1")
    public String kakaoPayGet() {
        return "kakaoPay";

    }

    // 인덱스 페이지
    @GetMapping("/")
    public ModelAndView home(HttpSession session, HttpServletRequest request) {
        mav = new ModelAndView();


        Cookie[] cookies = request.getCookies();

        String userId = "";

        // cookieLogin : 쿠키 로그인일 경우 처음 홈페이지 들어올 때 로그인 값 세션에 주입
        if(cookies != null){
            for(Cookie c : cookies) {
                if(c.getName().equals("idCookie")){     // 쿠키 이름 가져오기
                    userId = c.getValue();  // 쿠키 값 가져오기

                    userDTO login = udao.userLogin(userId);
                    session.setAttribute("login", login);

                }
            }

        }

        /*session.setAttribute("myNote",myNote);
        List<noteDTO> myNote = udao.myNote(login.getUserId());
                    mav.addObject("Note",myNote);*/




        mav.setViewName("/index");

        List<auDTO> auIndex = maindao.auIndex();

        List<itemDTO> itemIndex = maindao.itemIndex();

        for(int i =0 ; i < auIndex.size(); i++){
            // 현재 시간을 불러온다
            Date now = new Date(System.currentTimeMillis());

            // 현재시간 - 등록시간
            long deadDate = (auIndex.get(i).getAuDeadline().getTime()-now.getTime())/1000;

            // 현재시간과 등록시간의 차이가
            // 60초 이전의 값
            if(deadDate >= 0){
                auIndex.get(i).setAucDate("곧");
            }
            // 1달 이후의 값
            if(deadDate >= (30*24*60*60)){
                deadDate = deadDate/(30*24*60*60);
                auIndex.get(i).setAucDate(deadDate+"달 후");
            }
            // 1일 이후의 값
            if(deadDate >=(24*60*60)){
                deadDate = deadDate/(24*60*60);
                auIndex.get(i).setAucDate(deadDate+"일 후");
            }
            // 1시간 이후의 값
            if(deadDate >= (60*60)){
                deadDate = deadDate/(60*60);
                auIndex.get(i).setAucDate(deadDate+"시간 후");
            }
            // 60초 이후의 값
            if(deadDate >= 60){
                deadDate = deadDate/60;
                auIndex.get(i).setAucDate(deadDate+"분 후");
            }




            // 지역 정보 출력
            // reData에 auRegionInfo로 reCity, reNine 정보를 가져온다.
            reDTO reData = audao.auRegionInfo(auIndex.get(i).getAuRegion());

            // itRegion에 reCity와 reNine값을 합친 값을 넣어준다.
            auIndex.get(i).setAucRegion(reData.getReCity() + " " + reData.getReNine());
        }

        mav.addObject("auList",auIndex);

        for(int i =0 ; i < itemIndex.size(); i++){
            // 현재 시간을 불러온다
            Date now = new Date(System.currentTimeMillis());

            // 현재시간 - 등록시간
            long deadDate = (now.getTime()-itemIndex.get(i).getItemDate().getTime())/1000;

            // 현재시간과 등록시간의 차이가
            // 60초 이전의 값
            if(deadDate >= 0){
                itemIndex.get(i).setItDate("방금 전");
            }
            // 1달 이후의 값
            if(deadDate >= (30*24*60*60)){
                deadDate = deadDate/(30*24*60*60);
                itemIndex.get(i).setItDate(deadDate+"달 전");
            }
            // 1일 이후의 값
            if(deadDate >=(24*60*60)){
                deadDate = deadDate/(24*60*60);
                itemIndex.get(i).setItDate(deadDate+"일 전");
            }
            // 1시간 이후의 값
            if(deadDate >= (60*60)){
                deadDate = deadDate/(60*60);
                itemIndex.get(i).setItDate(deadDate+"시간 전");
            }
            // 60초 이후의 값
            if(deadDate >= 60){
                deadDate = deadDate/60;
                itemIndex.get(i).setItDate(deadDate+"분 전");
            }






            // 지역 정보 출력
            // reData에 itemRegionInfo로 reCity, reNine 정보를 가져온다.
            reDTO reData = itemdao.itemRegionInfo(itemIndex.get(i).getItemRegion());

            // itRegion에 reCity와 reNine값을 합친 값을 넣어준다.
            itemIndex.get(i).setItRegion(reData.getReCity() + " " + reData.getReNine());
        }
        mav.addObject("itemList",itemIndex);


        List<auDTO> auList = audao.auList1();

        for(int i=0 ; i<auList.size();i++) {

            repository.createAuctionRoomDTO(auList.get(i).getAuNum());
        }


        return mav;
    }

    @GetMapping("/session")
    public String session(HttpSession session) {

        userDTO users = udao.session1();
        session.setAttribute("login",users);


        return "redirect:/index";
    }

    // 인덱스페이지
    @GetMapping("/index")
    public ModelAndView index(HttpSession session, HttpServletRequest request) {

        mav = new ModelAndView();

        Cookie[] cookies = request.getCookies();

        String userId = "";

        // cookieLogin : 쿠키 로그인일 경우 처음 홈페이지 들어올 때 로그인 값 세션에 주입
        if(cookies != null){
            for(Cookie c : cookies) {
                if(c.getName().equals("idCookie")){     // 쿠키 이름 가져오기
                    userId = c.getValue();  // 쿠키 값 가져오기

                    userDTO login = udao.userLogin(userId);
                    session.setAttribute("login", login);

                }
            }

        }





        mav.setViewName("/index");

        List<auDTO> auIndex = maindao.auIndex();

        List<itemDTO> itemIndex = maindao.itemIndex();

        for(int i =0 ; i < auIndex.size(); i++){
            // 현재 시간을 불러온다
            Date now = new Date(System.currentTimeMillis());

            // 현재시간 - 등록시간
            long deadDate = (auIndex.get(i).getAuDeadline().getTime()-now.getTime())/1000;

            // 현재시간과 등록시간의 차이가
            // 60초 이전의 값
            if(deadDate >= 0){
                auIndex.get(i).setAucDate("곧");
            }
            // 1달 이후의 값
            if(deadDate >= (30*24*60*60)){
                deadDate = deadDate/(30*24*60*60);
                auIndex.get(i).setAucDate(deadDate+"달 후");
            }
            // 1일 이후의 값
            if(deadDate >=(24*60*60)){
                deadDate = deadDate/(24*60*60);
                auIndex.get(i).setAucDate(deadDate+"일 후");
            }
            // 1시간 이후의 값
            if(deadDate >= (60*60)){
                deadDate = deadDate/(60*60);
                auIndex.get(i).setAucDate(deadDate+"시간 후");
            }
            // 60초 이후의 값
            if(deadDate >= 60){
                deadDate = deadDate/60;
                auIndex.get(i).setAucDate(deadDate+"분 후");
            }




            // 지역 정보 출력
            // reData에 auRegionInfo로 reCity, reNine 정보를 가져온다.
            reDTO reData = audao.auRegionInfo(auIndex.get(i).getAuRegion());

            // itRegion에 reCity와 reNine값을 합친 값을 넣어준다.
            auIndex.get(i).setAucRegion(reData.getReCity() + " " + reData.getReNine());
        }

        mav.addObject("auList",auIndex);


        for(int i =0 ; i < itemIndex.size(); i++){
            // 현재 시간을 불러온다
            Date now = new Date(System.currentTimeMillis());

            // 현재시간 - 등록시간
            long deadDate = (now.getTime()-itemIndex.get(i).getItemDate().getTime())/1000;

            // 현재시간과 등록시간의 차이가
            // 60초 이전의 값
            if(deadDate >= 0){
                itemIndex.get(i).setItDate("방금 전");
            }
            // 1달 이후의 값
            if(deadDate >= (30*24*60*60)){
                deadDate = deadDate/(30*24*60*60);
                itemIndex.get(i).setItDate(deadDate+"달 전");
            }
            // 1일 이후의 값
            if(deadDate >=(24*60*60)){
                deadDate = deadDate/(24*60*60);
                itemIndex.get(i).setItDate(deadDate+"일 전");
            }
            // 1시간 이후의 값
            if(deadDate >= (60*60)){
                deadDate = deadDate/(60*60);
                itemIndex.get(i).setItDate(deadDate+"시간 전");
            }
            // 60초 이후의 값
            if(deadDate >= 60){
                deadDate = deadDate/60;
                itemIndex.get(i).setItDate(deadDate+"분 전");
            }






            // 지역 정보 출력
            // reData에 itemRegionInfo로 reCity, reNine 정보를 가져온다.
            reDTO reData = itemdao.itemRegionInfo(itemIndex.get(i).getItemRegion());

            // itRegion에 reCity와 reNine값을 합친 값을 넣어준다.
            itemIndex.get(i).setItRegion(reData.getReCity() + " " + reData.getReNine());
        }
        mav.addObject("itemList",itemIndex);



        return mav;
    }


    @GetMapping("/14-404")
    public String g() { return "14-404"; }

}
