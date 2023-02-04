package com.sss.pop.serviceImpl;

import com.sss.pop.dto.noDTO;
import com.sss.pop.dto.pageDTO;
import com.sss.pop.service.noService;
import com.sss.pop.dao.noDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
@RequiredArgsConstructor
public class noServiceImpl implements noService {

    private ModelAndView mav;

    private final noDAO nodao;


    @Override
    public ModelAndView noWrite(noDTO notice) {
        mav = new ModelAndView();

        int result = nodao.noWrite(notice);

        if(result > 0){
            mav.setViewName("redirect:/noList");
        } else {
            mav.setViewName("noWriteForm");
        }

        return mav;
    }

    @Override
    public ModelAndView noList(int page,String category,String keyword) {
        mav = new ModelAndView();
        // 한 화면에 보여줄 페이지 번호 갯수
        int block = 5;

        // 한 화면에 보여줄 게시글 갯수
        int limit = 7;

        // 전체 게시글 갯수
        String where = "";
        if(category.equals("제목")){
            where += "noTitle LIKE '%" + keyword + "%'";
        } else if(category.equals("내용")){
            where += "noContent LIKE '%" + keyword + "%'";
        } else {
            where += "noTitle LIKE '%%'";
        }

        int count = nodao.noCount(where);


        int startRow = (page - 1) * limit + 1;  // 1
        int endRow = page * limit;              // 5

        int maxPage = (int)(Math.ceil((double) count / limit ));
        int startPage = (((int)(Math.ceil((double) page / block))) - 1) * block + 1;
        int endPage = startPage + block - 1;

        if(endPage > maxPage) {
            endPage = maxPage;
        }

        // 페이징 객체 생성
        pageDTO paging = new pageDTO();

        paging.setPage(page);           // 1
        paging.setStartRow(startRow);   // 1
        paging.setEndRow(endRow);       // 5
        paging.setMaxPage(maxPage);     // 8
        paging.setStartPage(startPage); // 1
        paging.setEndPage(endPage);     // 5
        paging.setLimit(limit);         // 5
        paging.setCategory(category);
        paging.setKeyword(keyword);
        paging.setWhere(where);

        List<noDTO> pagingList = nodao.pNoList(paging);

        // model
        mav.addObject("pagingList", pagingList);
        mav.addObject("paging", paging);

        // view
        mav.setViewName("noList");

        return mav;
    }

    @Override
    public ModelAndView noView(int noNum) {
        mav = new ModelAndView();

        noDTO noView = nodao.noView(noNum);

        mav.addObject("view", noView);

        mav.setViewName("noView");

        return mav;
    }

    @Override
    public ModelAndView noDelete(int noNum) {

        mav = new ModelAndView();

        int result = nodao.noDelete(noNum);

        if(result > 0){
            mav.setViewName("redirect:/noList");
        } else {
            mav.setViewName("noView?noNum="+noNum);
        }
        return mav;
    }

    @Override
    public ModelAndView noModiForm(int noNum) {

        mav = new ModelAndView();

        noDTO notice = nodao.noView(noNum);

        mav.addObject("Modi",notice);
        mav.setViewName("noModiForm");

        return mav;
    }

    @Override
    public ModelAndView noModify(noDTO notice) {

        mav = new ModelAndView();

        int result = nodao.noModify(notice);

        if(result > 0){
            mav.setViewName("redirect:/noList");
        } else {
            mav.setViewName("noModiForm");
        }

        return mav;
    }


}
