package com.sss.pop.controller;

import com.sss.pop.dao.boDAO;
import com.sss.pop.dto.boDTO;
import com.sss.pop.dto.userDTO;
import com.sss.pop.service.boService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class boController {

    private ModelAndView mav = new ModelAndView();

    private final boService bsvc;

    private final boDAO bdao;


    // boWriteForm : 게시물 작성페이지 요청
    @GetMapping("/boWriteForm")
    public String boWrite(){
        return "boWrite";
    }

    // boWrite : 게시물 작성
    @PostMapping("/boWrite")
    public ModelAndView boWrite(@ModelAttribute boDTO board) throws IOException {
        System.out.println("[1]controller\n board : "+board);

        mav = bsvc.boWrite(board);

        System.out.println("[5]controller\n mav : "+mav);

        return mav;
    }

    // boList : 게시물 목록
    @GetMapping("/boList")
    public ModelAndView boList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                  @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
                               @RequestParam(value = "category", required = false, defaultValue = "") String category,
                               @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword){

        mav = bsvc.boList(page, limit,category,keyword);

        return mav;
    }

    // boView : 게시물 상세보기
    @GetMapping("boView")
    public ModelAndView boView(@RequestParam("boNum") int boNum,
                               @SessionAttribute(name = "login", required = false) userDTO user,
                               HttpServletRequest request,
                               HttpServletResponse response){

        /* 조회수 로직 */
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(boNum+"boardView")) {
                    oldCookie = cookie;
                }
            }
        }
        if(user != null) {

            if (oldCookie != null) {
                if (!oldCookie.getValue().contains("[" + user.getUserId() + "]")) {
                    bdao.boHit(boNum);
                    oldCookie.setValue(oldCookie.getValue() + "_[" + user.getUserId() + "]");
                    oldCookie.setPath("/");
                    oldCookie.setMaxAge(60 * 60 * 8);
                    response.addCookie(oldCookie);
                }
            } else {
                bdao.boHit(boNum);
                Cookie newCookie = new Cookie(boNum+"boardView","[" + user.getUserId() + "]");
                newCookie.setPath("/");
                newCookie.setMaxAge(60 * 60 * 8);
                response.addCookie(newCookie);
            }
        }else {
            if (oldCookie != null) {
                if (!oldCookie.getValue().contains("[" + boNum + "]")) {
                    bdao.boHit(boNum);
                    oldCookie.setValue(oldCookie.getValue() + "_[" + boNum + "]");
                    oldCookie.setPath("/");
                    oldCookie.setMaxAge(60 * 60 * 8);
                    response.addCookie(oldCookie);
                }
            } else {
                bdao.boHit(boNum);
                Cookie newCookie = new Cookie(boNum+"boardView","[" + boNum + "]");
                newCookie.setPath("/");
                newCookie.setMaxAge(60 * 60 * 8);
                response.addCookie(newCookie);
            }

        }



        mav = bsvc.boView(boNum);

        return mav;
    }

    // boModifyForm : 게시물 수정페이지 요청
    @GetMapping("boModifyForm")
    public ModelAndView boModifyForm(@RequestParam("boNum") int boNum){
        mav = bsvc.boModifyForm(boNum);
        return mav;
    }

    // boModify : 게시물 수정
    @PostMapping("boModify")
    public ModelAndView boModify(@ModelAttribute boDTO board) throws IOException {
        mav = bsvc.boModify(board);
        return mav;
    }

    // boDelete : 게시물 삭제
    @GetMapping("boDelete")
    public ModelAndView boDelete(@RequestParam("boNum") int boNum){
        mav = bsvc.boDelete(boNum);
        return mav;
    }

    @PostMapping("checkAjax")
    public @ResponseBody boDTO checkAjax(@RequestParam("boNum") int boNum){
        return bsvc.checkAjax(boNum);
    }


}
