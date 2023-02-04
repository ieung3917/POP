package com.sss.pop.auc;


import com.sss.pop.dto.auDTO;
import com.sss.pop.dto.caDTO;
import com.sss.pop.dto.reDTO;
import com.sss.pop.dto.userDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class auController {

    private ModelAndView mav;

    private final auService ausvc;

    private final HttpSession session;

    private final AuctionRoomRepository repository;

    private final auDAO audao;







   // 채팅서버 만들기
    @GetMapping(value = "/server")
    public String create(){

        List<auDTO> auList = audao.auList1();


         for(int i=0 ; i<auList.size();i++) {

             System.out.println((auList.get(i).getAuNum()));

             repository.createAuctionRoomDTO(auList.get(i).getAuNum());
         }

        return "redirect:/index";
    }


    // auList : 경매품 리스트
    @GetMapping("/auList")
    public ModelAndView auList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "category", required = false, defaultValue = "0") int category,
                               @RequestParam(value = "search", required = false, defaultValue = "") String search) {
        session.removeAttribute("category");
        session.removeAttribute("search");

        return ausvc.auList(page,category,search);
    }

    /*//채팅방 조회
    @GetMapping("/num")
    public void getRoom(String auNum, Model model){

        log.info("# get Chat Room, roomID : " + auNum);

        model.addAttribute("auNum", repository.findRoomById(auNum));
    }*/


    // auView : 경매품 상세보기

    @GetMapping("/auView")
    public ModelAndView auView(@RequestParam("auNum") int auNum,
                               @SessionAttribute(name = "login", required = false) userDTO user,
                               HttpServletRequest request,
                               HttpServletResponse response){

        /* 조회수 로직 */
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(auNum+"auView")) {
                    oldCookie = cookie;
                }
            }
        }
        if(user != null) {

            if (oldCookie != null) {
                if (!oldCookie.getValue().contains("[" + user.getUserId() + "]")) {
                    audao.auHit(auNum);
                    oldCookie.setValue(oldCookie.getValue() + "_[" + user.getUserId() + "]");
                    oldCookie.setPath("/");
                    oldCookie.setMaxAge(60 * 60 * 8);
                    response.addCookie(oldCookie);
                }
            } else {
                audao.auHit(auNum);
                Cookie newCookie = new Cookie(auNum+"auView","[" + user.getUserId() + "]");
                newCookie.setPath("/");
                newCookie.setMaxAge(60 * 60 * 8);
                response.addCookie(newCookie);
            }
        }else {
            if (oldCookie != null) {
                if (!oldCookie.getValue().contains("[" + auNum  + "]")) {
                    audao.auHit(auNum);
                    oldCookie.setValue(oldCookie.getValue() + "_[" + auNum + "]");
                    oldCookie.setPath("/");
                    oldCookie.setMaxAge(60 * 60 * 8);
                    response.addCookie(oldCookie);
                }
            } else {
                audao.auHit(auNum);
                Cookie newCookie = new Cookie(auNum+"auView","[" + auNum + "]");
                newCookie.setPath("/");
                newCookie.setMaxAge(60 * 60 * 8);
                response.addCookie(newCookie);
            }

        }

        return ausvc.auView(auNum);
    }

    // itemAdd : 판매할 중고품 등록 메소드
    @PostMapping("/auAdd")
    public ModelAndView auAdd(auDTO auction,reDTO region, caDTO category) throws IOException {
        return ausvc.auAdd(auction,region,category);
    }

    // 경매물품 등록
    @GetMapping("/auAdd")
    public String auAdd()  {
        return "/auAdd";
    }

    // selectCaMain : mainCategory 선택시 subCategory 변경
    @PostMapping("/selectCaMain1")
    public @ResponseBody List<caDTO> selectCaMain(@RequestParam("caMain") String caMain) {
        List<caDTO> caList = ausvc.selectCaMain(caMain);
        return caList;
    }

    // selectReCity : reCity 선택시 subCategory 변경
    @PostMapping("/selectReCity1")
    public @ResponseBody List<reDTO> selectReCity(@RequestParam("reCity") String reCity) {
        List<reDTO> reList = ausvc.selectReCity(reCity);
        return reList;
    }

    // 경매 구매 내역
    @GetMapping("/auBList")
    public ModelAndView auBList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                @RequestParam("userId")String userId){
        return ausvc.auBList(page, keyword, userId);
    }

    // 경매 판매 내역
    @GetMapping("/auSList")
    public ModelAndView auSList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                @RequestParam("userId")String userId){
        return ausvc.auSList(page, keyword, userId);
    }



}
