package com.sss.pop.controller;

import com.sss.pop.dto.icDTO;
import com.sss.pop.service.icService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class icController {

    private ModelAndView mav = new ModelAndView();

    private final icService icsvc;

    // itemCommentWrite : 판매 중고품에 대한 댓글 작성
    @PostMapping("/itemCommentWrite")
    public @ResponseBody List<icDTO> itemCommentWrite(@RequestParam("icItemNum") int icItemNum,
                                @RequestParam("icContent") String icContent,
                                @RequestParam("icWriter") String icWriter) {
        return icsvc.itemCommentWrite(icItemNum, icContent, icWriter);
    }

    // itemCommentList : 판매 중고품의 댓글 불러오기
    @PostMapping("/itemCommentList")
    public @ResponseBody List<icDTO> itemCommentList(@RequestParam ("itemNum")int itemNum){
        System.out.println(itemNum);
        return icsvc.itemCommentList(itemNum);
    }

    // itemCommentDelete : 판매 중고품의 댓글 삭제하기
    @PostMapping ("/itemCommentDelete")
    public @ResponseBody List<icDTO> itemCommentDelete(@RequestParam("icNum")int icNum,
                                                       @RequestParam("icItemNum")int icItemNum){
        return icsvc.itemCommentDelete(icNum,icItemNum);
    }

}
