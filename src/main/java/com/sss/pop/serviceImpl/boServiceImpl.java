package com.sss.pop.serviceImpl;

import com.sss.pop.dao.boDAO;
import com.sss.pop.dto.boDTO;
import com.sss.pop.dto.pageDTO;
import com.sss.pop.service.boService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class boServiceImpl implements boService {

    private ModelAndView mav;

    private final boDAO bdao;


    // boWrite : 게시물 작성
    @Override
    public ModelAndView boWrite(boDTO board) throws IOException {
        System.out.println("[2]service\n board : "+board);
        mav = new ModelAndView();

        // 1. 파일 불러오기
        // 업로드한 파일 그 자체
        MultipartFile boFile = board.getBoFile();

        // 2. 파일 선택여부 확인
        if (!boFile.isEmpty()) {

            // 3. 파일 저장 위치 설정(상대경로)
            Path path = Paths.get(System.getProperty("user.dir")
                    , "src/main/resources/static/fileUpload");

            // 4. 기존에 있던 파일 삭제하기
//            String deletePath = path + "/" + member.getProfileName();
//            File deleteFile = new File(deletePath);
//
//            if(deleteFile.exists()) {
//                deleteFile.delete();
//            }

            // 5. 파일 이름 불러오기
            // 업로드한 파일의 이름
            String originalFileName = boFile.getOriginalFilename();

            // 6. 난수 생성하기
            String uuid = UUID.randomUUID().toString().substring(0, 8);

            // 7. 업로드 할 파일이름 생성하기(3번 + 2번)
            String boFileName = uuid + "_" + originalFileName;

            // 8. 파일 선택시 MemberDTO member객체의 profileName 필드에 업로드파일 이름 저장
            board.setBoFileName(boFileName);

            // 9. 파일 업로드
            // 빨간줄 생성 시 throws Exception 처리 해주어야함(service,service interface, controller 모두)
            String savePath = path + "/" + boFileName;
            boFile.transferTo(new File(savePath)); // 회원가입 정보가 제대로 저장되었을 경우 savePath에 저장하기 위함
        }

        //////////////////////////////////////////////////////
        System.out.println("[3]dao\n board : "+board);

        int result = bdao.boWrite(board);

        System.out.println("[4]service\n result : "+result);
        //////////////////////////////////////////////////////

        if(result>0){
            mav.setViewName("redirect:/boList");
        } else {
            mav.setViewName("boWrite");
        }

        return mav;
    }

    // boList : 게시물 목록
    @Override
    public ModelAndView boList(int page, int limit, String category, String keyword) {
        mav = new ModelAndView();

        // 한 화면에 보여줄 페이지 번호 갯수
        int block = 5;

        // 한 화면에 보여줄 게시글 갯수
        // int limit = 5;
        System.out.println(category+keyword);

        String where = "";
        if(category.equals("subject_content")){
            where += "boTitle LIKE '%" + keyword + "%' OR boContent LIKE '%"+keyword+"%'";
        } else if(category.equals("subject")){
            where += "boTitle LIKE '%" + keyword + "%'";
        } else if(category.equals("content")){
            where += "boContent LIKE '%" + keyword + "%'";
        } else if(category.equals("writer_name")){
            where += "boWriter LIKE '%" + keyword + "%'";
        } else {
            where += "boTitle LIKE '%%'";
        }

        // 전체 게시글 갯수 : 59
        int boCount = bdao.boCount(where);

//		System.out.println("bCount : "+bCount);

        int startRow = (page - 1) * limit + 1;
        int endRow = page * limit;

        // ceil() 함수 : 올림 기능
        int maxPage = (int) (Math.ceil((double) boCount / limit));
        int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
        int endPage = startPage + block - 1;

        // 오류 방지 코드
        if (endPage > maxPage) {
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


        List<boDTO> boList = bdao.boList(paging);

        mav.addObject("boList", boList);
        mav.addObject("paging", paging);

        mav.setViewName("boList");

        return mav;
    }

    // boView : 게시물 상세보기
    @Override
    public ModelAndView boView(int boNum) {
        mav = new ModelAndView();

        boDTO board = bdao.boView(boNum);

        mav.addObject("view", board);
        mav.setViewName("boView");

        return mav;
    }

    @Override
    public ModelAndView boModifyForm(int boNum) {
        mav = new ModelAndView();
        boDTO board = bdao.boView(boNum);

        mav.setViewName("boModify");
        mav.addObject("modify", board);

        return mav;
    }

    @Override
    public ModelAndView boModify(boDTO board) throws IOException {
        System.out.println("[2]service\n board : "+board);
        mav = new ModelAndView();

        // 1. 파일 불러오기
        // 업로드한 파일 그 자체
        MultipartFile boFile = board.getBoFile();

        // 2. 파일 선택여부 확인
        if (!boFile.isEmpty()) {

            // 3. 파일 저장 위치 설정(상대경로)
            Path path = Paths.get(System.getProperty("user.dir")
                    , "src/main/resources/static/fileUpload");

            // 4. 기존에 있던 파일 삭제하기
            String deletePath = path + "/" + board.getBoFileName();
            File deleteFile = new File(deletePath);

            if(deleteFile.exists()) {
                deleteFile.delete();
            }

            // 5. 파일 이름 불러오기
            // 업로드한 파일의 이름
            String originalFileName = boFile.getOriginalFilename();

            // 6. 난수 생성하기
            String uuid = UUID.randomUUID().toString().substring(0, 8);

            // 7. 업로드 할 파일이름 생성하기(3번 + 2번)
            String boFileName = uuid + "_" + originalFileName;

            // 8. 파일 선택시 MemberDTO member객체의 profileName 필드에 업로드파일 이름 저장
            board.setBoFileName(boFileName);

            // 9. 파일 업로드
            // 빨간줄 생성 시 throws Exception 처리 해주어야함(service,service interface, controller 모두)
            String savePath = path + "/" + boFileName;
            boFile.transferTo(new File(savePath)); // 회원가입 정보가 제대로 저장되었을 경우 savePath에 저장하기 위함
        }

        //////////////////////////////////////////////////////
        System.out.println("[3]dao\n board : "+board);

        int result = bdao.boModify(board);

        System.out.println("[4]service\n result : "+result);
        //////////////////////////////////////////////////////

        if(result>0){
            mav.setViewName("redirect:/boView?boNum="+board.getBoNum());
        } else {
            mav.setViewName("boWrite");
        }

        return mav;
    }

    @Override
    public ModelAndView boDelete(int boNum) {
        mav = new ModelAndView();
        boDTO board = bdao.boView(boNum);
        int result = bdao.boDelete(boNum);

        if(result>0){
            Path path = Paths.get(System.getProperty("user.dir")
                    , "src/main/resources/static/fileUpload");;

            // 기존에 있던 파일 지우기
            String deletePath = path + "/" + board.getBoFileName();
            File deleteFile = new File(deletePath);

            if(deleteFile.exists()){
                deleteFile.delete();
            }

            mav.setViewName("redirect:/boList");
        } else {
            mav.setViewName("index");
        }

        return mav;
    }

    @Override
    public boDTO checkAjax(int boNum) {
        return bdao.boView(boNum);
    }


}
