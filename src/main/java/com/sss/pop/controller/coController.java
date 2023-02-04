package com.sss.pop.controller;

import com.sss.pop.dto.coDTO;
import com.sss.pop.service.coService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class coController {

    private final coService csvc;


    // cList : 댓글 불러오기
    @GetMapping("/coList")
    public @ResponseBody List<coDTO> coList(@RequestParam("cbNum") int cbNum) {

//		System.out.println("[1] jsp->controller\n cbNum : "+cbNum);

        List<coDTO> coList = csvc.coList(cbNum);

//		System.out.println("[5] service->controller\n commentList : "+commentList);

        return coList;
    }

    // cmtWrite : 댓글 작성
    @RequestMapping(value = "/coWrite", method = RequestMethod.POST)
    public @ResponseBody List<coDTO> coWrite(@ModelAttribute coDTO comments) {

//		System.out.println("[1] jsp->controller\n comment : "+comment);

        List<coDTO> coList = csvc.coWrite(comments);

//		System.out.println("[5] service->controller\n commentList : "+commentList);

        return coList;
    }

    // cmtDelete : 댓글 삭제
    @RequestMapping(value = "/coDelete", method = RequestMethod.POST)
    public @ResponseBody List<coDTO> coDelete(@ModelAttribute coDTO comments) {

//		System.out.println("[1] jsp->controller\n comment : "+comment);

        List<coDTO> coList = csvc.coDelete(comments);

//		System.out.println("[5] service->controller\n commentList : "+commentList);

        return coList;
    }

    // cmtModify : 댓글 수정
    @RequestMapping(value = "/coModify", method = RequestMethod.POST)
    public @ResponseBody List<coDTO> cmtModify(@ModelAttribute coDTO comments) {

//		System.out.println("[1] jsp->controller\n comment : "+comment);

        List<coDTO> coList = csvc.coModify(comments);

//		System.out.println("[5] service->controller\n commentList : "+commentList);

        return coList;
    }
}
