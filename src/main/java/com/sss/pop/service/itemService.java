package com.sss.pop.service;

import com.sss.pop.dto.caDTO;
import com.sss.pop.dto.itemDTO;
import com.sss.pop.dto.reDTO;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;


public interface itemService {
    ModelAndView itemAdd(itemDTO item, reDTO region, caDTO category) throws IOException;

    ModelAndView itemList(int page, int category, String search) throws ParseException;

    ModelAndView itemView(int itemNum);

    List<caDTO> selectCaMain(String caMain);

    List<reDTO> selectReCity(String reCity);

    ModelAndView itemDelete(int itemNum);

    ModelAndView itemModifyForm(int itemNum);

    ModelAndView itemModify(itemDTO item, reDTO region, caDTO category) throws IOException;

    String itemLike(int ilItem, String ilUser);

    String itemLikeCheck(int ilItem, String ilUser);

    String userCashCheck(int itemNum, int itemPrice, String itemSeller, String itemName, String userId);

    String itemTakeCheck(int itemNum, int itemPrice, String itemSeller, String itemBuyer);

    int userCash(String userId);
}
