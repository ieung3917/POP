package com.sss.pop.serviceImpl;

import com.sss.pop.dao.noteDAO;
import com.sss.pop.dto.noteCategoryDTO;
import com.sss.pop.dto.noteDTO;
import com.sss.pop.dto.pageDTO;
import com.sss.pop.dto.userDTO;
import com.sss.pop.service.noteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class noteServiceImpl implements noteService {
    private ModelAndView mav;

    private final noteDAO notedao;


    // noteGetList : 받은 쪽지함 페이지 요청(로그인 아이디 + 페이징처리)
    @Override
    public ModelAndView noteGetList(HttpSession session, int page, int limit, String category, String keyword) {
        mav = new ModelAndView();

        // 로그인 된 아이디 인스턴스에 저장
        userDTO login = (userDTO) session.getAttribute("login");
        String userId = null;

        if(login != null) {
            userId = login.getUserId();
        }


        userDTO user = new userDTO();
        user.setUserId(userId);

        // 카테고리, 키워드 인스턴스에 저장
        noteCategoryDTO noteCategory = new noteCategoryDTO();
        noteCategory.setCategory(category);
        noteCategory.setKeyword(keyword);

        // 넘겨줄 데이터를 담을 Map 객체 생성
        Map<String, Object> map = new HashMap<String, Object>();

        // Map 영역에 넘어온 값을 객체 타입으로 저장
        map.put("user", user);
        map.put("noteCategory", noteCategory);


        // 페이징 처리
        // 한 화면에 보여줄 페이지 번호 갯수
        int block = 5;

        // 한 화면에 보여줄 쪽지 갯수
        // int limit = 10;

        // 전체 받은 쪽지 갯수
        int count;

        if(category.equals("")){
            count = notedao.noteGetCount(map);
        } else if(category.equals("ALL")) {
            count = notedao.noteGetAllCount(map);
        } else if(category.equals("NOTETITLE")) {
            count = notedao.noteGetTitleCount(map);
        } else {
            count = notedao.noteGetTitleCount(map);
        }


        int startRow = (page - 1) * limit + 1;
        int endRow = page * limit;

        // ceil() 함수 : 올림 기능
        int maxPage = (int) (Math.ceil((double) count / limit));
        int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
        int endPage = startPage + block - 1;

        // 오류 방지 코드
        if (endPage > maxPage) {
            if(maxPage == 0){
                maxPage = startPage;
            }
            endPage = maxPage;
        }

        // 페이징 정보 인스턴스에 저장
        pageDTO paging = new pageDTO();

        paging.setPage(page);
        paging.setStartRow(startRow);
        paging.setEndRow(endRow);
        paging.setMaxPage(maxPage);
        paging.setStartPage(startPage);
        paging.setEndPage(endPage);
        paging.setLimit(limit);


        // paging 인스턴스 Map객체에 저장
        map.put("paging", paging);

//        System.out.println("user : "+user);
//        System.out.println("noteCategory : "+noteCategory);
//        System.out.println("paging : "+paging);

        // 리스트 목록을 담을 List타입 객체
        List<noteDTO> noteGetList;

        if(category.equals("")){
            noteGetList = notedao.noteGetList(map);
        } else if(category.equals("ALL")) {
            noteGetList = notedao.noteGetAllList(map);
        } else if(category.equals("NOTETITLE")) {
            noteGetList = notedao.noteGetTitleList(map);
        } else {
            noteGetList = notedao.noteGetTitleList(map);
        }

//        System.out.println("[3] service\n noteGetList : "+noteGetList);
//        System.out.println("service \n user : "+user+", paging : "+paging);
//        List<noteDTO> noteGetList = notedao.noteGetList(userID_paging_map);
//        System.out.println("dao->service \n noteGetList : "+noteGetList);


        // model
        mav.addObject("noteGetList", noteGetList);
        mav.addObject("paging", paging);
        mav.addObject("noteCategory", noteCategory);

        // view
        mav.setViewName("noteGetList");

        return mav;
    }

    // noteSendList : 보낸 쪽지함 페이지 요청(로그인 아이디 + 페이징처리)
    @Override
    public ModelAndView noteSendList(HttpSession session, int page, int limit, String category, String keyword) {
        mav = new ModelAndView();

        // 로그인 된 아이디 인스턴스에 저장
        userDTO login = (userDTO) session.getAttribute("login");
        String userId = login.getUserId();

        userDTO user = new userDTO();
        user.setUserId(userId);

        // 카테고리, 키워드 인스턴스에 저장
        noteCategoryDTO noteCategory = new noteCategoryDTO();
        noteCategory.setCategory(category);
        noteCategory.setKeyword(keyword);

        // 넘겨줄 데이터를 담을 Map 객체 생성
        Map<String, Object> map = new HashMap<String, Object>();

        // Map 영역에 넘어온 값을 객체 타입으로 저장
        map.put("user", user);
        map.put("noteCategory", noteCategory);


        // 페이징 처리
        // 한 화면에 보여줄 페이지 번호 갯수
        int block = 5;

        // 한 화면에 보여줄 쪽지 갯수
        // int limit = 5;

        // 전체 보낸 쪽지 갯수
        int count;

        if(category.equals("")){
            count = notedao.noteSendCount(map);
        } else if(category.equals("ALL")) {
            count = notedao.noteSendAllCount(map);
        } else if(category.equals("NOTETITLE")) {
            count = notedao.noteSendTitleCount(map);
        } else {
            count = notedao.noteSendTitleCount(map);
        }


        int startRow = (page - 1) * limit + 1;
        int endRow = page * limit;

        // ceil() 함수 : 올림 기능
        int maxPage = (int) (Math.ceil((double) count / limit));
        int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
        int endPage = startPage + block - 1;

        // 오류 방지 코드
        if (endPage > maxPage) {
            if(maxPage == 0){
                maxPage = startPage;
            }
            endPage = maxPage;
        }

        // 페이징 정보 인스턴스에 저장
        pageDTO paging = new pageDTO();

        paging.setPage(page);
        paging.setStartRow(startRow);
        paging.setEndRow(endRow);
        paging.setMaxPage(maxPage);
        paging.setStartPage(startPage);
        paging.setEndPage(endPage);
        paging.setLimit(limit);


        // paging 인스턴스 Map객체에 저장
        map.put("paging", paging);

        // 리스트 목록을 담을 List타입 객체
        List<noteDTO> noteSendList;

        if(category.equals("")){
            noteSendList = notedao.noteSendList(map);
        } else if(category.equals("ALL")) {
            noteSendList = notedao.noteSendAllList(map);
        } else if(category.equals("NOTETITLE")) {
            noteSendList = notedao.noteSendTitleList(map);
        } else {
            noteSendList = notedao.noteSendTitleList(map);
        }

//        System.out.println("service \n user : "+user+", paging : "+paging);
//        List<noteDTO> noteGetList = notedao.noteGetList(userID_paging_map);
//        System.out.println("dao->service \n noteGetList : "+noteGetList);


        // model
        mav.addObject("noteSendList", noteSendList);
        mav.addObject("paging", paging);
        mav.addObject("noteCategory", noteCategory);

        // view
        mav.setViewName("noteSendList");

        return mav;
    }








    // noteView : 쪽지 상세보기
    @Override
    public ModelAndView noteView(int noteSeq, String pageName) {
        mav = new ModelAndView();

        noteDTO noteView = notedao.noteView(noteSeq);

        if(noteView != null){
            notedao.noteCheck(noteSeq);
        }

        mav.addObject("noteView", noteView);
        mav.addObject("pageName", pageName);
        mav.setViewName("noteView");

        return mav;
    }


    // noteWrite : 쪽지 답장하기
    @Override
    public ModelAndView noteWrite(noteDTO note, HttpSession session) {
        mav = new ModelAndView();

//        System.out.println("[2] service\n note : "+note);

        int result = notedao.noteWrite(note);

//        System.out.println("[3] service\n result : "+result);

        userDTO login = (userDTO) session.getAttribute("login");
        String userId = login.getUserId();

        if(result>0){
            mav.setViewName("redirect:/noteGetList?userId="+userId);
        } else {
            mav.setViewName("redirect:/noteGetList?userId="+userId);
        }

        return mav;
    }

    // noteGetDelete : 받은 쪽지 삭제하기
    @Override
    public ModelAndView noteDelete(int noteSeq, String pageName) {
        mav = new ModelAndView();

        noteDTO note = notedao.noteView(noteSeq);

        int noteDelCheck = note.getNoteDelCheck();

        int result = 0;

        if(pageName.equals("get")) {

            if (noteDelCheck == 0) {  // 받은 쪽지가 먼저 삭제된 경우
                // 1로 바꿈
                result = notedao.noteGetUpdate(noteSeq);
            } else if (noteDelCheck == 2) {  // 보낸 쪽지가 먼저 삭제되고 난 후 받은 쪽지를 삭제할 경우
                // 삭제 한다
                result = notedao.noteDelete(noteSeq);
            }

            if (result > 0) {
                mav.setViewName("redirect:/noteGetList");
            } else {
                mav.setViewName("redirect:/index");
            }

        } else if(pageName.equals("send")) {

            if (noteDelCheck == 0) {  // 보낸 쪽지가 먼저 삭제된 경우
                // 2로 바꿈
                result = notedao.noteSendUpdate(noteSeq);
            } else if (noteDelCheck == 1) {  // 받은 쪽지가 먼저 삭제되고 난 후 보낸 쪽지를 삭제할 경우
                // 삭제 한다
                result = notedao.noteDelete(noteSeq);
            }

            if (result > 0) {
                mav.setViewName("redirect:/noteSendList");
            } else {
                mav.setViewName("redirect:/index");
            }

        }

        return mav;
    }

    // noteGetNoneCheckCnt : 안 읽은 쪽지 갯수 header에 가져오기(ajax)
    @Override
    public int noteGetNoneCheckCnt(String userId) {

        int result = notedao.noteGetNoneCheckCnt(userId);

        return result;
    }

    @Override
    public List<noteDTO> noteMynote(String userId) {
        List<noteDTO> mynoteList = notedao.noteMynote(userId);
        return mynoteList;
    }
}
