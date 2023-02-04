package com.sss.pop.service;

import com.sss.pop.dto.noteDTO;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface noteService {
    // noteGetList : 받은 쪽지함 페이지 요청(로그인 아이디 + 페이징처리)
    ModelAndView noteGetList(HttpSession session, int page, int limit, String category, String keyword);

    // noteSendList : 보낸 쪽지함 페이지 요청(로그인 아이디 + 페이징처리)
    ModelAndView noteSendList(HttpSession session, int page, int limit, String category, String keyword);




    // noteView : 쪽지 상세보기
    ModelAndView noteView(int noteSeq, String pageName);

    // noteWrite : 쪽지 답장하기
    ModelAndView noteWrite(noteDTO note, HttpSession session);

    // noteGetDelete : 받은 쪽지 삭제하기
    ModelAndView noteDelete(int noteSeq, String pageName);

    // noteGetNoneCheckCnt : 안 읽은 쪽지 갯수 header에 가져오기(ajax)
    int noteGetNoneCheckCnt(String userId);

    List<noteDTO> noteMynote(String userId);
}
