package com.sss.pop.serviceImpl;

import com.sss.pop.dao.itemDAO;
import com.sss.pop.dto.*;
import com.sss.pop.service.itemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class itemServiceImpl implements itemService {

    private final itemDAO itemdao;

    private final HttpSession session;

    private ModelAndView mav;

    // itemAdd : 판매할 중고물품 추가
    @Override
    public ModelAndView itemAdd(itemDTO item, reDTO region, caDTO category) throws IOException {

        System.out.println("service item : " + item);
        System.out.println("service region : " + region);
        System.out.println("service category : " + category);

        mav = new ModelAndView();

        // 사진 파일 등록
        MultipartFile itemPhoto = item.getItemPhoto();

        if (!itemPhoto.isEmpty()) {
            Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/itemPhoto");

            String uuid = UUID.randomUUID().toString().substring(0, 8);

            String originalFileName = itemPhoto.getOriginalFilename();

            String itemPhotoName = uuid + "_" + originalFileName;

            item.setItemPhotoName(itemPhotoName);

            String savePath = path + "/" + itemPhotoName;

            itemPhoto.transferTo(new File(savePath));
        }

        MultipartFile itemPhoto1 = item.getItemPhoto1();

        if (!itemPhoto1.isEmpty()) {
            Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/itemPhoto");

            String uuid = UUID.randomUUID().toString().substring(0, 8);

            String originalFile1Name = itemPhoto1.getOriginalFilename();

            String itemPhoto1Name = uuid + "_" + originalFile1Name;

            item.setItemPhoto1Name(itemPhoto1Name);

            String savePath = path + "/" + itemPhoto1Name;

            itemPhoto1.transferTo(new File(savePath));
        }

        MultipartFile itemPhoto2 = item.getItemPhoto2();

        if (!itemPhoto2.isEmpty()) {
            Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/itemPhoto");

            String uuid = UUID.randomUUID().toString().substring(0, 8);

            String originalFile2Name = itemPhoto2.getOriginalFilename();

            String itemPhoto2Name = uuid + "_" + originalFile2Name;

            item.setItemPhoto2Name(itemPhoto2Name);

            String savePath = path + "/" + itemPhoto2Name;

            itemPhoto2.transferTo(new File(savePath));
        }

        MultipartFile itemPhoto3 = item.getItemPhoto3();

        if (!itemPhoto3.isEmpty()) {
            Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/itemPhoto");

            String uuid = UUID.randomUUID().toString().substring(0, 8);

            String originalFile3Name = itemPhoto3.getOriginalFilename();

            String itemPhoto3Name = uuid + "_" + originalFile3Name;

            item.setItemPhoto3Name(itemPhoto3Name);

            String savePath = path + "/" + itemPhoto3Name;

            itemPhoto3.transferTo(new File(savePath));
        }

        // 카테고리 시퀀스번호 caSeq 찾아오기
        item.setItemCategory(itemdao.caSeqFind(category));

        // 지역 시퀀스번호 reSeq 찾아오기
        item.setItemRegion(itemdao.reSeqFind(region));

        try {
            // 중고물품 등록 성공시 (에러나 예외처리가 없을 경우)
            itemdao.itemAdd(item);
            mav.setViewName("redirect:/index");
        } catch (Exception e) {
            // 에러 발생시
            System.out.println("물건 등록 실패!");
            mav.setViewName("14-404");
        }
        return mav;
    }

    // selectCaMain : mainCategory 선택시 subCategory 변경
    @Override
    public List<caDTO> selectCaMain(String caMain) {
        List<caDTO> caList = itemdao.selectCaMain(caMain);

        return caList;
    }

    // selectReCity : reCity 선택시 subCategory 변경
    @Override
    public List<reDTO> selectReCity(String reCity) {
        List<reDTO> reList = itemdao.selectReCity(reCity);

        return reList;
    }

    @Override
    public ModelAndView itemDelete(int itemNum) {
        mav = new ModelAndView();

        itemdao.itemDelete(itemNum);
        itemdao.itemCommentDelete(itemNum);

        mav.setViewName("redirect:/index");
        return mav;
    }

    // 중고품 수정 페이지
    @Override
    public ModelAndView itemModifyForm(int itemNum) {
        mav = new ModelAndView();
        System.out.println(itemdao.itemView(itemNum));

        mav.setViewName("itemModify");
        mav.addObject("itemView", itemdao.itemView(itemNum));
        return mav;
    }

    // 중고품 수정
    @Override
    public ModelAndView itemModify(itemDTO item, reDTO region, caDTO category) throws IOException {
        System.out.println("[2] service item : " + item);
        mav = new ModelAndView();

        itemdao.itemPhotoReset(item.getItemNum());

        // 사진 파일 등록
        MultipartFile itemPhoto = item.getItemPhoto();

        if (!itemPhoto.isEmpty()) {
            Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/itemPhoto");

            String uuid = UUID.randomUUID().toString().substring(0, 8);

            String originalFileName = itemPhoto.getOriginalFilename();

            String itemPhotoName = uuid + "_" + originalFileName;

            item.setItemPhotoName(itemPhotoName);

            String savePath = path + "/" + itemPhotoName;

            itemPhoto.transferTo(new File(savePath));
        }

        MultipartFile itemPhoto1 = item.getItemPhoto1();

        if (!itemPhoto1.isEmpty()) {
            Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/itemPhoto");

            String uuid = UUID.randomUUID().toString().substring(0, 8);

            String originalFile1Name = itemPhoto1.getOriginalFilename();

            String itemPhoto1Name = uuid + "_" + originalFile1Name;

            item.setItemPhoto1Name(itemPhoto1Name);

            String savePath = path + "/" + itemPhoto1Name;

            itemPhoto1.transferTo(new File(savePath));
        }

        MultipartFile itemPhoto2 = item.getItemPhoto2();

        if (!itemPhoto2.isEmpty()) {
            Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/itemPhoto");

            String uuid = UUID.randomUUID().toString().substring(0, 8);

            String originalFile2Name = itemPhoto2.getOriginalFilename();

            String itemPhoto2Name = uuid + "_" + originalFile2Name;

            item.setItemPhoto2Name(itemPhoto2Name);

            String savePath = path + "/" + itemPhoto2Name;

            itemPhoto2.transferTo(new File(savePath));
        }

        MultipartFile itemPhoto3 = item.getItemPhoto3();

        if (!itemPhoto3.isEmpty()) {
            Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/itemPhoto");

            String uuid = UUID.randomUUID().toString().substring(0, 8);

            String originalFile3Name = itemPhoto3.getOriginalFilename();

            String itemPhoto3Name = uuid + "_" + originalFile3Name;

            item.setItemPhoto3Name(itemPhoto3Name);

            String savePath = path + "/" + itemPhoto3Name;

            itemPhoto3.transferTo(new File(savePath));
        }

        // 카테고리 시퀀스번호 caSeq 찾아오기
        item.setItemCategory(itemdao.caSeqFind(category));

        // 지역 시퀀스번호 reSeq 찾아오기
        item.setItemRegion(itemdao.reSeqFind(region));

        System.out.println(item);

        try {
            // 중고물품 수정 성공시 (에러나 예외처리가 없을 경우)
            itemdao.itemModify(item);
            mav.setViewName("redirect:/itemView?itemNum="+ item.getItemNum());
        } catch (Exception e) {
            // 에러 발생시
            System.out.println("물건 수정 실패!");
            mav.setViewName("14-404");
        }
        return mav;
    }

    // 중고품 리스트
    @Override
    public ModelAndView itemList(int page, int category, String search) throws ParseException {
        mav = new ModelAndView();

        // 한 화면에 보여줄 페이지 번호 갯수
        int block = 5;

        // 한 화면에 보여줄 물품 갯수
        int limit = 9;

        int count;

        // 전체 중고품 갯수
        if(category  == 0){
            if(search == null){
                // 카테고리 X / 검색 X
                count = itemdao.itemCount();
            } else {
                // 카테고리 X / 검색 O
                count = itemdao.itemCountS(search);
            }
        } else {
            if(search == null){
                // 카테고리 O / 검색 X
                count = itemdao.itemCountC(category);
            } else {
                // 카테고리 O / 검색 O
                Map<String, Object> map = new HashMap<String, Object>();

                caDTO cate = new caDTO();
                cate.setCaSeq(category);

                seDTO sear = new seDTO();
                sear.setSearch(search);

                map.put("cate", cate);
                map.put("sear", sear);

                count = itemdao.itemCountSC(map);
            }
        }

        System.out.println(count);

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

        // 페이징 객체 생성
        pageDTO paging = new pageDTO();

        paging.setPage(page);
        paging.setStartRow(startRow);
        paging.setEndRow(endRow);
        paging.setMaxPage(maxPage);
        paging.setStartPage(startPage);
        paging.setEndPage(endPage);
        paging.setLimit(limit);

        List<itemDTO> itemList;

        // 카테고리 값이 존재하지 않을경우
        if(category == 0 ) {
            // 검색값이 존재하지 않을 경우
            if(search == null){
                itemList = itemdao.itemList(paging);
                // 카테고리가 없고, 검색값이 있을경우
            } else {
                seDTO sear = new seDTO();
                sear.setSearch(search);

                Map<String, Object> map = new HashMap<String,Object>();

                map.put("paging", paging);
                map.put("sear", sear);

                session.removeAttribute("search");
                session.setAttribute("search", sear);
                itemList = itemdao.itemListS(map);
                mav.addObject("sear",sear);
            }

        }

        // 카테고리 값이 존재할경우
        else {
            if(search == null) {
                caDTO cate = new caDTO();
                cate.setCaSeq(category);

                Map<String, Object> map = new HashMap<String, Object>();

                map.put("paging", paging);
                map.put("cate", cate);

                session.removeAttribute("category");
                session.setAttribute("category", cate);
                itemList = itemdao.itemListC(map);

                mav.addObject("cate", cate);
            }
            // 카테고리 값이 있고, 검색값이 존재할경우
            else {
                caDTO cate = new caDTO();
                cate.setCaSeq(category);

                seDTO sear = new seDTO();
                sear.setSearch(search);

                Map<String, Object> map = new HashMap<String, Object>();

                map.put("paging", paging);
                map.put("cate", cate);
                map.put("sear", sear);

                session.removeAttribute("category");
                session.setAttribute("category", cate);
                session.removeAttribute("search");
                session.setAttribute("search", sear);

                itemList = itemdao.itemListSC(map);

                mav.addObject("cate", cate);
                mav.addObject("sear",sear);
            }
        }

        // 시간&지역 출력 관련 정보
        for(int i =0 ; i < itemList.size(); i++){
            // 현재 시간을 불러온다
            Date now = new Date(System.currentTimeMillis());

            // 현재시간 - 등록시간
            long deadDate = (now.getTime()-itemList.get(i).getItemDate().getTime())/1000;

            // 현재시간과 등록시간의 차이가
            // 60초 이전의 값
            if(deadDate >= 0){
                itemList.get(i).setItDate("방금 전");
            }
            // 1달 이후의 값
            if(deadDate >= (30*24*60*60)){
                deadDate = deadDate/(30*24*60*60);
                itemList.get(i).setItDate(deadDate+"달 전");
            }
            // 1일 이후의 값
            if(deadDate >=(24*60*60)){
                deadDate = deadDate/(24*60*60);
                itemList.get(i).setItDate(deadDate+"일 전");
            }
            // 1시간 이후의 값
            if(deadDate >= (60*60)){
                deadDate = deadDate/(60*60);
                itemList.get(i).setItDate(deadDate+"시간 전");
            }
            // 60초 이후의 값
            if(deadDate >= 60){
                deadDate = deadDate/60;
                itemList.get(i).setItDate(deadDate+"분 전");
            }

            // 지역 정보 출력
            // reData에 itemRegionInfo로 reCity, reNine 정보를 가져온다.
            reDTO reData = itemdao.itemRegionInfo(itemList.get(i).getItemRegion());

            // itRegion에 reCity와 reNine값을 합친 값을 넣어준다.
            itemList.get(i).setItRegion(reData.getReCity() + " " + reData.getReNine());
        }

        mav.setViewName("itemList");
        mav.addObject("itemList", itemList);
        mav.addObject("paging", paging);

        return mav;
    }

    // 중고품 상세정보 확인
    @Override
    public ModelAndView itemView(int itemNum) {
        mav = new ModelAndView();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        // 판매물건 정보 가져오기
        itemDTO itemView = itemdao.itemView(itemNum);
        itemView.setItDate(dateFormat.format(itemView.getItemDate()));

        // 판매자 정보 가져오기
        userDTO userInfo = itemdao.itemSellerInfo(itemView.getItemSeller());

        // 거래 지역 정보 가져오기
        reDTO reInfo = itemdao.itemRegionInfo(itemView.getItemRegion());

        // 카테고리 정보 가져오기
        caDTO caInfo = itemdao.itemCategoryInfo(itemView.getItemCategory());

        // 조회수 증가
        itemdao.itemHit(itemNum);

        System.out.println(itemView);
        System.out.println(userInfo);

        mav.setViewName("itemView");
        mav.addObject("itemView", itemView);
        mav.addObject("userInfo", userInfo);
        mav.addObject("reInfo", reInfo);
        mav.addObject("caInfo", caInfo);

        return mav;
    }

    // 중고품 좋아요
    @Override
    public String itemLike(int ilItem, String ilUser) {

        ilDTO il = new ilDTO();

        il.setIlItem(ilItem);

        il.setIlUser(ilUser);
        il.setIlItem(ilItem);

        Integer result = itemdao.itemLikeCheck(il);

        if(result == null) {
            itemdao.itemLike(il);
            itemdao.itemLikeUP(ilItem);
            return "Y";
        } else {
            itemdao.itemLikeDelete(il);
            itemdao.itemLikeDown(ilItem);
            return "N";
        }
    }

    // 중고품 좋아요 체크
    @Override
    public String itemLikeCheck(int ilItem, String ilUser) {
        ilDTO il = new ilDTO();

        il.setIlItem(ilItem);

        il.setIlUser(ilUser);
        il.setIlItem(ilItem);

        Integer result = itemdao.itemLikeCheck(il);

        if (result == null){
            return "N";
        } else {
            return "Y";
        }
    }

    // 해당 사용자 계좌의 돈을 확인.
    @Override
    public String userCashCheck(int itemNum, int itemPrice, String itemSeller, String itemName, String userId) {

        Map<String, Object> map = new HashMap<String, Object>();

        itemDTO item = new itemDTO();
        item.setItemNum(itemNum);
        item.setItemPrice(itemPrice);
        item.setItemSeller(itemSeller);
        item.setItemName(itemName);

        userDTO user = new userDTO();
        user.setUserId(userId);

        map.put("item", item);
        map.put("user", user);

        String result = itemdao.userCashCheck(map);

        if(result.equals("Y")){

            // 실제 결제 (계좌에서 차감)
            itemdao.itemPayment(map);

            // 판매중으로 변경 + 해당 유저ID를 Buyer로 지정
            itemdao.itemCheckChange(map);

            // 판매자에게 쪽지 보내기
            itemdao.itemSendBuyNote(map);

            return result;
        } else {
            return result;
        }
    }

    @Override
    public String itemTakeCheck(int itemNum, int itemPrice, String itemSeller, String itemBuyer) {

        itemDTO item = new itemDTO();

        item.setItemNum(itemNum);
        item.setItemPrice(itemPrice);
        item.setItemSeller(itemSeller);
        item.setItemBuyer(itemBuyer);

        // 판매완료로 변경
        itemdao.itemTakeCheck(item);

        // 판매자의 캐시 증가
        itemdao.itemSell(item);

        // 판매자에게 쪽지 보내기
        itemdao.itemSendSellNote(item);

        return null;
    }

    @Override
    public int userCash(String userId) {
        return itemdao.userCash(userId);
    }


}
