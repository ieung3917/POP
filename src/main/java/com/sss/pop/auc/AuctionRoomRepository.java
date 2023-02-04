package com.sss.pop.auc;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class AuctionRoomRepository {

    private Map<String, AuctionRoomDTO> auctionRoomDTOMap;

    // 경매품 상세보기(웹소켓 연결)


    @PostConstruct
    private void init(){
        auctionRoomDTOMap = new LinkedHashMap<>();
    }


    public AuctionRoomDTO findRoomById(String id){

        return auctionRoomDTOMap.get(id);
    }

    public AuctionRoomDTO createAuctionRoomDTO(int auNum){
        AuctionRoomDTO num = AuctionRoomDTO.create(String.valueOf(auNum));
        auctionRoomDTOMap.put(num.getAuNum(), num);

        return num;
    }
}
