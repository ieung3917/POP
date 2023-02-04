package com.sss.pop.dao;

import com.sss.pop.dto.noteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface noteDAO {

    // 받은 쪽지함
    // noteCount : 받은 쪽지 전체 갯수
    int noteGetCount(Map<String, Object> map);

    // noteGetAllCount : 제목+내용 받은 쪽지 전체 갯수
    int noteGetAllCount(Map<String, Object> map);

    // noteGetTitleCount : (제목) OR (아이디) 받은 쪽지 전체 갯수
    int noteGetTitleCount(Map<String, Object> map);




    // noteGetList : 받은 쪽지함(메인) 페이지 요청(로그인 아이디 포함)
    List<noteDTO> noteGetList(Map<String, Object> map);

    // noteGetAllList : 제목+내용 검색 목록(페이징처리 o)
    List<noteDTO> noteGetAllList(Map<String, Object> map);

    // noteGetTitleList : (제목) OR (아이디) 검색 목록(페이징처리 o)
    List<noteDTO> noteGetTitleList(Map<String, Object> map);







    // 보낸 쪽지함
    // noteSendCount : 보낸 쪽지 전체 갯수
    int noteSendCount(Map<String, Object> map);

    // noteSendAllCount : 제목+내용 쪽지 전체 갯수
    int noteSendAllCount(Map<String, Object> map);

    // noteSendTitleCount : 보낸 쪽지 (제목) OR (아이디) 검색 목록 갯수(페이징처리o)
    int noteSendTitleCount(Map<String, Object> map);





    // noteSendList : 보낸 쪽지함 페이지 요청(로그인 아이디 포함)
    List<noteDTO> noteSendList(Map<String, Object> map);

    // noteSendAllList : 보낸 쪽지 제목+내용 검색 목록(페이징처리o)
    List<noteDTO> noteSendAllList(Map<String, Object> map);

    // noteSendTitleList : 보낸 쪽지 (제목) OR (아이디) 검색 목록(페이징처리o)
    List<noteDTO> noteSendTitleList(Map<String, Object> map);









    // 쪽지 기능들
    // noteView : 쪽지 상세보기(보낸거,받은거 모두)
    noteDTO noteView(int noteSeq);

    // noteCheck : 쪽지 확인 여부
    void noteCheck(int noteSeq);

    // noteWrite : 쪽지 답장하기(쓰기)
    int noteWrite(noteDTO note);

    // noteGetUpdate : 받은 쪽지가 먼저 삭제된 경우
    int noteGetUpdate(int noteSeq);

    // noteDelete : 먼저 ()쪽지가 삭제되고 난 후 다른 사용자가 쪽지를 삭제할 경우
    int noteDelete(int noteSeq);

    // noteSendUpdate : 보낸 쪽지가 먼저 삭제될 경우
    int noteSendUpdate(int noteSeq);

    // noteGetNoneCheckCnt : 안 읽은 쪽지 갯수 header에 가져오기(ajax)
    int noteGetNoneCheckCnt(String userId);

    List<noteDTO> noteMynote(String userId);
}
