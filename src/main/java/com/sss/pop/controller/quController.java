package com.sss.pop.controller;

import com.sss.pop.dto.quDTO;
import com.sss.pop.service.quService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class quController {

    private final quService qusvc;

    // 문의사항 목록
    @GetMapping("/quList")
    public ModelAndView quList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "category", required = false, defaultValue = "") String category,
                               @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                               @RequestParam("userId") String userId) {

        return qusvc.quList(page,category,keyword,userId);
    }

    // 문의사항 답변x 목록
    @GetMapping("/quNAList")
    public ModelAndView quNAList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "category", required = false, defaultValue = "") String category,
                               @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                               @RequestParam("userId") String userId) {

        return qusvc.quNAList(page,category,keyword,userId);
    }

    // 문의사항 답변o 목록
    @GetMapping("/quAList")
    public ModelAndView quAList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "category", required = false, defaultValue = "") String category,
                               @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                               @RequestParam("userId") String userId) {

        return qusvc.quAList(page,category,keyword,userId);
    }

    // 문의사항 상세보기
    @GetMapping("/quView")
    public ModelAndView quView(@RequestParam("quNum") int quNum) {
        return qusvc.quView(quNum);
    }

    // 문의사항 작성폼 가기
    @GetMapping("/quWriteForm")
    public String quWriteForm() {
        return "quWriteForm";
    }

    // 문의사항 작성
    @PostMapping("/quWrite")
    public ModelAndView quWrite(@ModelAttribute quDTO quest) {
        return qusvc.quWrite(quest);
    }

    // 문의사항 삭제
    @GetMapping("/quDelete")
    public ModelAndView quDelete(@RequestParam("quNum") int quNum){ return qusvc.quDelete(quNum); }

    // 문의사항 수정폼 가기
    @GetMapping("/quModiForm")
    public ModelAndView quModiForm(@RequestParam("quNum") int quNum){ return qusvc.quModiForm(quNum); }

    // 문의사항 수정
    @PostMapping("/quModify")
    public ModelAndView quModify(@ModelAttribute quDTO quest){ return qusvc.quModify(quest); }

    // 문의사항 답변달기
    @PostMapping("/quAWrite")
    public ModelAndView quAWrite(@ModelAttribute quDTO quest){ return qusvc.quAWrite(quest);}

    @Autowired
    private JavaMailSender mailSender;


    // 문의사항 이메일 문의
    @PostMapping("/quEWrite")
    public String mailSending(HttpServletRequest request) {


        String setfrom = "syh22133@gmail.com";
        String tomail = request.getParameter("tomail"); // 받는 사람 이메일
        String title = request.getParameter("title"); // 제목
        String content = "이름 : " + request.getParameter("name")
                + "\n이메일 주소 : " + request.getParameter("email")
                + "\n오류 내용 : " + request.getParameter("content"); // 내용

        System.out.println("setfrom : " + setfrom);
        System.out.println("tomail : " + tomail);
        System.out.println("title : " + title);
        System.out.println("content : " + content);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

            messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
            messageHelper.setTo(tomail); // 받는사람 이메일
            messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
            messageHelper.setText(content); // 메일 내용

            mailSender.send(message);
        } catch (Exception e) {
            System.out.println(e);
        }

        return "index";
    }
}
