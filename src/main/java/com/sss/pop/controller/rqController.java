package com.sss.pop.controller;

import com.sss.pop.dto.rqDTO;
import com.sss.pop.service.rqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class rqController {

    private final rqService rqsvc;

    // 자주찾는 질문 목록
    @GetMapping("/rqList")
    public ModelAndView rqList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "category", required = false, defaultValue = "") String category,
                               @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword
                               ) {

        return rqsvc.rqList(page, category, keyword);
    }

    // 자주찾는 질문 상세보기
    @GetMapping("/rqView")
    public ModelAndView rqView(@RequestParam("rqNum") int rqNum) {
        return rqsvc.rqView(rqNum);
    }

    // 자주찾는 질문 작성폼 가기
    @GetMapping("/rqWriteForm")
    public String rqWriteForm() {
        return "rqWriteForm";
    }

    // 자주찾는 질문 작성
    @PostMapping("/rqWrite")
    public ModelAndView rqWrite(@ModelAttribute rqDTO repque) {
        return rqsvc.rqWrite(repque);
    }

    // 자주찾는 질문 삭제
    @GetMapping("/rqDelete")
    public ModelAndView rqDelete(@RequestParam("rqNum") int rqNum){ return rqsvc.rqDelete(rqNum); }

    // 자주찾는 질문 수정폼 가기
    @GetMapping("/rqModiForm")
    public ModelAndView rqModiForm(@RequestParam("rqNum") int rqNum){ return rqsvc.rqModiForm(rqNum); }

    // 자주찾는 질문 수정
    @PostMapping("/rqModify")
    public ModelAndView rqModify(@ModelAttribute rqDTO repque){ return rqsvc.rqModify(repque); }

}
