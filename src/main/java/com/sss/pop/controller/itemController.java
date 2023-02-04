package com.sss.pop.controller;

import com.sss.pop.dto.caDTO;
import com.sss.pop.dto.itemDTO;
import com.sss.pop.dto.reDTO;
import com.sss.pop.service.itemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class itemController {

    private ModelAndView mav = new ModelAndView();

    private final itemService itemsvc;

    private final HttpSession session;

    // itemAddForm : 중고품 등록 페이지로 이동
    @GetMapping("/itemAdd")
    public String itemAddForm(HttpSession session) {
        return "itemAdd";
    }

    // itemAdd : 판매할 중고품 등록 메소드
    @PostMapping("/itemAdd")
    public ModelAndView itemAdd(itemDTO item, reDTO region, caDTO category) throws IOException {
        System.out.println(item);
        return itemsvc.itemAdd(item, region, category);
    }

    // selectCaMain : mainCategory 선택시 subCategory 변경
    @PostMapping("/selectCaMain")
    public @ResponseBody List<caDTO> selectCaMain(@RequestParam("caMain") String caMain) {
        List<caDTO> caList = itemsvc.selectCaMain(caMain);
        return caList;
    }

    // selectReCity : reCity 선택시 subCategory 변경
    @PostMapping("/selectReCity")
    public @ResponseBody List<reDTO> selectReCity(@RequestParam("reCity") String reCity) {
        List<reDTO> reList = itemsvc.selectReCity(reCity);
        return reList;
    }

    // itemList : 판매되는 중고품 리스트 페이지
    @GetMapping("/itemList")
    public ModelAndView itemList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                 @RequestParam(value = "category", required = false, defaultValue = "0") int category,
                                 @RequestParam(value = "search", required = false, defaultValue = "") String search) throws ParseException {
        session.removeAttribute("category");
        session.removeAttribute("search");
        return itemsvc.itemList(page,category,search);
    }

    // itemView : 판매되는 중고품의 상세정보 페이지
    @GetMapping("/itemView")
    public ModelAndView itemView(@RequestParam("itemNum") int itemNum) {
        return itemsvc.itemView(itemNum);
    }

    // itemModifyForm (Get) : 수정페이지 이동시 예외처리
    @GetMapping("/itemModifyForm")
    public String itemModifyForm(){
          return "14-404";
    }

    // itemModifyForm : 중고품 수정 페이지로 이동
    @PostMapping("/itemModifyForm")
    public ModelAndView itemModifyForm(@RequestParam("itemNum")int itemNum) {
        return itemsvc.itemModifyForm(itemNum);
    }

    // itemModify : 중고품 수정
    @PostMapping("/itemModify")
    public ModelAndView itemModify(itemDTO item ,reDTO region, caDTO category) throws IOException {
        return itemsvc.itemModify(item, region, category);
    }

    // itemDelete : 판매되는 중고품 내역 삭제
    @PostMapping("/itemDelete")
    public ModelAndView itemDelete(@RequestParam("itemNum")int itemNum){
        return itemsvc.itemDelete(itemNum);
    }

    // itemLike : 중고품 좋아요
    @PostMapping ("/itemLike")
    public @ResponseBody String itemLike(@RequestParam("ilItem")int ilItem,
                                         @RequestParam("ilUser")String ilUser){
        return itemsvc.itemLike(ilItem,ilUser);
    }

    // itemLikeCheck : 중고품 좋아요 여부 확인
    @PostMapping ("/itemLikeCheck")
    public @ResponseBody String itemLikeCheck(@RequestParam("ilItem")int ilItem,
                                              @RequestParam("ilUser")String ilUser) {
        return itemsvc.itemLikeCheck(ilItem,ilUser);
    }

    // userCashCheck : 중고품 구매시, 회원 계좌 확인
    @PostMapping("/userCashCheck")
    public @ResponseBody String userCashCheck(@RequestParam("itemNum")int itemNum,
                                              @RequestParam("itemPrice")int itemPrice,
                                              @RequestParam("itemSeller")String itemSeller,
                                              @RequestParam("itemName")String itemName,
                                              @RequestParam("userId")String userId){
        return itemsvc.userCashCheck(itemNum, itemPrice, itemSeller, itemName, userId);
    }

    // itemTakeCheck : 수취확인버튼
    @PostMapping("/itemTakeCheck")
    public @ResponseBody String itemTakeCheck(@RequestParam("itemNum")int itemNum,
                                              @RequestParam("itemPrice")int itemPrice,
                                              @RequestParam("itemSeller")String itemSeller,
                                              @RequestParam("itemBuyer")String itemBuyer){
        return itemsvc.itemTakeCheck(itemNum, itemPrice, itemSeller, itemBuyer);
    }

    // userCash : 캐시 확인
    @PostMapping("/userCash")
    public @ResponseBody int userCash(@RequestParam("userId")String userId){
        return itemsvc.userCash(userId);
    }

}
