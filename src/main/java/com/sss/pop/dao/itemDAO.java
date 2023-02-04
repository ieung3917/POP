package com.sss.pop.dao;

import com.sss.pop.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface itemDAO {

    int caSeqFind(caDTO category);

    int reSeqFind(reDTO region);

    void itemAdd(itemDTO item);

    int itemCount();

    int itemCountC(int caSeq);

    int itemCountS(String search);

    int itemCountSC(Map<String, Object> map);

    List<itemDTO> itemList(pageDTO paging);

    List<itemDTO> itemListC(Map<String, Object> map);

    List<itemDTO> itemListS(Map<String, Object> map);

    List<itemDTO> itemListSC(Map<String, Object> map);

    itemDTO itemView(int itemNum);

    void itemHit(int itemNum);

    userDTO itemSellerInfo(String itemSeller);

    reDTO itemRegionInfo(int itemRegion);

    caDTO itemCategoryInfo(int itemCategory);

    List<caDTO> selectCaMain(String caMain);

    List<reDTO> selectReCity(String reCity);

    List<itemDTO> selectCategoryItemList(int caSeq);

    int itemNumCheck(itemDTO item);

    void itemDelete(int itemNum);

    void itemCommentDelete(int itemNum);

    void itemModify(itemDTO item);

    void itemLike(ilDTO il);

    Integer itemLikeCheck(ilDTO il);

    void itemLikeDelete(ilDTO il);

    // 상점 주인의 판매 물품 갯수
    int itemCnt(String userId);

    String userCashCheck(Map<String, Object> map);

    void itemPayment(Map<String, Object> map);

    void itemCheckChange(Map<String, Object> map);

    void itemSendBuyNote(Map<String, Object> map);

    void itemLikeUP(int ilItem);

    void itemLikeDown(int ilItem);

    void itemPhotoReset(int itemNum);

    void itemTakeCheck(itemDTO item);

    void itemSell(itemDTO item);

    void itemSendSellNote(itemDTO item);

    int userCash(String userId);
}
