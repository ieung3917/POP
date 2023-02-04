package com.sss.pop.serviceImpl;

import com.sss.pop.dao.rqDAO;
import com.sss.pop.dto.pageDTO;
import com.sss.pop.dto.rqDTO;
import com.sss.pop.service.rqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
@RequiredArgsConstructor
public class rqServiceImpl implements rqService {

    private ModelAndView mav;

    private final rqDAO rqdao;
    @Override
    public ModelAndView rqList(int page, String category, String keyword) {

        mav = new ModelAndView();
        // 한 화면에 보여줄 페이지 번호 갯수
        int block = 5;

        // 한 화면에 보여줄 게시글 갯수
        int limit = 7;

        // 전체 게시글 갯수
        String where = "";


        if(category.equals("제목")){
            where += "rqTitle LIKE '%" + keyword + "%'";
        } else if(category.equals("내용")){
            where += "rqContent LIKE '%" + keyword + "%'";
        } else {
            where += "rqTitle LIKE '%%'";
        }

        int count = rqdao.rqCount(where);

        int startRow = (page - 1) * limit + 1;
        int endRow = page * limit;

        int maxPage = (int)(Math.ceil((double) count / limit ));
        int startPage = (((int)(Math.ceil((double) page / block))) - 1) * block + 1;
        int endPage = startPage + block - 1;

        if(endPage > maxPage) {
            endPage = maxPage;
        }

        // 페이징 객체 생성
        pageDTO paging = new pageDTO();

        paging.setPage(page);
        paging.setStartRow(startRow);
        paging.setEndRow(endRow);
        paging.setMaxPage(maxPage);
        paging.setStartPage(startPage);
        paging.setEndPage(endPage);
        paging.setLimit(limit);
        paging.setCategory(category);
        paging.setKeyword(keyword);
        paging.setWhere(where);

        List<rqDTO> pagingList = rqdao.pRqList(paging);

        // model
        mav.addObject("pagingList", pagingList);
        mav.addObject("paging", paging);

        // view
        mav.setViewName("rqList");

        return mav;
    }

    @Override
    public ModelAndView rqView(int rqNum) {
        mav = new ModelAndView();

        rqdao.rqHit(rqNum);

        rqDTO rqView = rqdao.rqView(rqNum);


        mav.addObject("view", rqView);

        mav.setViewName("rqView");

        return mav;
    }

    @Override
    public ModelAndView rqWrite(rqDTO repque) {

        mav = new ModelAndView();

        int result = rqdao.rqWrite(repque);

        if(result > 0){
            mav.setViewName("redirect:/rqList");
        } else {
            mav.setViewName("rqWriteForm");
        }

        return mav;
    }

    @Override
    public ModelAndView rqDelete(int rqNum) {
        mav = new ModelAndView();

        int result = rqdao.rqDelete(rqNum);

        if(result > 0){
            mav.setViewName("redirect:/rqList");
        } else {
            mav.setViewName("rqView?rqNum="+rqNum);
        }
        return mav;
    }

    @Override
    public ModelAndView rqModiForm(int rqNum) {
        mav = new ModelAndView();

        rqDTO repque = rqdao.rqView(rqNum);

        mav.addObject("Modi",repque);
        mav.setViewName("rqModiForm");

        return mav;
    }

    @Override
    public ModelAndView rqModify(rqDTO repque) {
        mav = new ModelAndView();

        int result = rqdao.rqModify(repque);

        if(result > 0){
            mav.setViewName("redirect:/rqList");
        } else {
            mav.setViewName("rqModiForm");
        }

        return mav;
    }

}
