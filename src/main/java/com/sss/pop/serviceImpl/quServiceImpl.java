package com.sss.pop.serviceImpl;

import com.sss.pop.dao.quDAO;
import com.sss.pop.dto.pageDTO;
import com.sss.pop.dto.quDTO;
import com.sss.pop.service.quService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Service
@RequiredArgsConstructor
public class quServiceImpl implements quService {

    private ModelAndView mav;

    private final quDAO qudao;
    @Override
    public ModelAndView quList(int page,String category,String keyword, String userId) {

        mav = new ModelAndView();
        // 한 화면에 보여줄 페이지 번호 갯수
        int block = 5;

        // 한 화면에 보여줄 게시글 갯수
        int limit = 7;

        String where = "";

        // 전체 게시글 갯수
        if(userId.equals("admin")){
            if(category.equals("제목")){
                where += "quTitle LIKE '%" + keyword + "%'";
            } else if(category.equals("내용")){
                where += "quContent LIKE '%" + keyword + "%'";
            } else {
                where += "quTitle LIKE '%%'";
            }
        } else {
            if(category.equals("제목")){
                where += "quTitle LIKE '%" + keyword + "%' AND quWriter = '"+ userId +"'";
            } else if(category.equals("내용")){
                where += "quContent LIKE '%" + keyword + "%' AND quWriter = '"+ userId +"'";
            } else {
                where += "quWriter = '"+ userId +"'";
            }
        }

        int count = qudao.quCount(where);

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

        List<quDTO> pagingList = qudao.pQuList(paging);

        // model
        mav.addObject("pagingList", pagingList);
        mav.addObject("paging", paging);

        // view
        mav.setViewName("quList");

        return mav;
    }

    @Override
    public ModelAndView quNAList(int page,String category,String keyword, String userId) {

        mav = new ModelAndView();
        // 한 화면에 보여줄 페이지 번호 갯수
        int block = 5;

        // 한 화면에 보여줄 게시글 갯수
        int limit = 5;

        String where = "";

        // 전체 게시글 갯수
        if(userId.equals("admin")){
            if(category.equals("제목")){
                where += "quTitle LIKE '%" + keyword + "%'";
            } else if(category.equals("내용")){
                where += "quContent LIKE '%" + keyword + "%'";
            } else {
                where += "quTitle LIKE '%%'";
            }
        } else {
            if(category.equals("제목")){
                where += "quTitle LIKE '%" + keyword + "%' AND quWriter = '"+ userId +"'";
            } else if(category.equals("내용")){
                where += "quContent LIKE '%" + keyword + "%' AND quWriter = '"+ userId +"'";
            } else {
                where += "quWriter = '"+ userId +"'";
            }
        }

        int count = qudao.quNACount(where);

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

        List<quDTO> pagingList = qudao.pQuNAList(paging);

        // model
        mav.addObject("pagingList", pagingList);
        mav.addObject("paging", paging);

        // view
        mav.setViewName("quNAList");

        return mav;
    }

    @Override
    public ModelAndView quAList(int page,String category,String keyword, String userId) {

        mav = new ModelAndView();
        // 한 화면에 보여줄 페이지 번호 갯수
        int block = 5;

        // 한 화면에 보여줄 게시글 갯수
        int limit = 5;

        String where = "";

        // 전체 게시글 갯수
        if(userId.equals("admin")){
            if(category.equals("제목")){
                where += "quTitle LIKE '%" + keyword + "%'";
            } else if(category.equals("내용")){
                where += "quContent LIKE '%" + keyword + "%'";
            } else {
                where += "quTitle LIKE '%%'";
            }
        } else {
            if(category.equals("제목")){
                where += "quTitle LIKE '%" + keyword + "%' AND quWriter = '"+ userId +"'";
            } else if(category.equals("내용")){
                where += "quContent LIKE '%" + keyword + "%' AND quWriter = '"+ userId +"'";
            } else {
                where += "quWriter = '"+ userId +"'";
            }
        }

        int count = qudao.quACount(where);

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

        List<quDTO> pagingList = qudao.pQuAList(paging);

        // model
        mav.addObject("pagingList", pagingList);
        mav.addObject("paging", paging);

        // view
        mav.setViewName("quAList");

        return mav;
    }

    @Override
    public ModelAndView quView(int quNum) {
        mav = new ModelAndView();

        qudao.quHit(quNum);

        quDTO quView = qudao.quView(quNum);


        mav.addObject("view", quView);

        mav.setViewName("quView");

        return mav;
    }

    @Override
    public ModelAndView quWrite(quDTO quest) {

        mav = new ModelAndView();

        int result = qudao.quWrite(quest);

        if(result > 0){
            mav.setViewName("redirect:/quList?userId="+quest.getQuWriter());
        } else {
            mav.setViewName("quWriteForm");
        }

        return mav;
    }

    @Override
    public ModelAndView quDelete(int quNum) {
        mav = new ModelAndView();

        int result = qudao.quDelete(quNum);

        if(result > 0){
            mav.setViewName("redirect:/quList");
        } else {
            mav.setViewName("quView?quNum="+quNum);
        }
        return mav;
    }

    @Override
    public ModelAndView quModiForm(int quNum) {
        mav = new ModelAndView();

        quDTO quest = qudao.quView(quNum);

        mav.addObject("Modi",quest);
        mav.setViewName("quModiForm");

        return mav;
    }

    @Override
    public ModelAndView quModify(quDTO quest) {
        mav = new ModelAndView();

        int result = qudao.quModify(quest);

        if(result > 0){
            mav.setViewName("redirect:/quList?userId="+quest.getQuWriter());
        } else {
            mav.setViewName("quModiForm");
        }

        return mav;
    }

    @Override
    public ModelAndView quAWrite(quDTO quest) {
        mav = new ModelAndView();

        qudao.quAWrite(quest);

        mav.setViewName("redirect:/quView?quNum=" + quest.getQuNum());

        return mav;
    }
}
