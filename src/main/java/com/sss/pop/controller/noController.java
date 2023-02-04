package com.sss.pop.controller;

import com.sss.pop.dto.noDTO;
import com.sss.pop.service.noService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class noController {

    private final noService nosvc;

    // 공지사항 목록
    @GetMapping("/noList")
    public ModelAndView noList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "category", required = false, defaultValue = "") String category,
                               @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {

        return nosvc.noList(page,category,keyword);
    }

    // 공지사항 상세보기
    @GetMapping("/noView")
    public ModelAndView noView(@RequestParam("noNum") int noNum) {
        return nosvc.noView(noNum);
    }

    // 공지사항 작성폼 가기
    @GetMapping("/noWriteForm")
    public String noWriteForm() {
        return "noWriteForm";
    }

    // 공지사항 등록
    @PostMapping("/noWrite")
    public ModelAndView noWrite(@ModelAttribute noDTO notice) {
        return nosvc.noWrite(notice);
    }

    // 공지사항 삭제
    @GetMapping("/noDelete")
    public ModelAndView noDelete(@RequestParam("noNum") int noNum){ return nosvc.noDelete(noNum); }

    // 공지사항 수정폼 가기
    @GetMapping("/noModiForm")
    public ModelAndView noModiForm(@RequestParam("noNum") int noNum){ return nosvc.noModiForm(noNum); }

    // 공지사항 수정
    @PostMapping("/noModify")
    public ModelAndView noModify(@ModelAttribute noDTO notice){ return nosvc.noModify(notice); }
}
