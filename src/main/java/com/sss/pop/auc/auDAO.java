package com.sss.pop.auc;

import com.sss.pop.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface auDAO {
   

    auDTO auView(int auNum);

    userDTO auSellerInfo(String auSeller);

    reDTO auRegionInfo(int auRegion);

    caDTO auCategoryInfo(int auCategory);

    void auHit(int auNum);

    userDTO userInfo(String userId);

    void cashUpdate(userDTO buyer);

    void auUpdate(auDTO auction);

    int auComplete(int auNum);

    void deadUpdate(int auNum);

    void auAdd(auDTO auction);

    List<caDTO> selectCaMain(String caMain);

    List<reDTO> selectReCity(String reCity);

    int caSeqFind(caDTO category);

    int reSeqFind(reDTO region);

    int auCount();

    int auCountS(String search);

    int auCountC(int category);

    int auCountSC(Map<String, Object> map);

    List<auDTO> auListS(Map<String, Object> map);

    List<auDTO> auListC(Map<String, Object> map);

    List<auDTO> auListSC(Map<String, Object> map);

    List<auDTO> auList1();

    List<auDTO> auList(pageDTO paging);

    int auBCount(String where);

    List<auDTO> auBList(pageDTO paging);

    int auSCount(String where);

    List<auDTO> auSList(pageDTO paging);


    void sendNotebuyer(auDTO auction);

    void sendNoteSeller(auDTO auction);

    void sendNoteComplete(auDTO auction);


    // audao : 상점 주인의 경매 판매 물품 갯수
    int auCnt(String userId);







}
