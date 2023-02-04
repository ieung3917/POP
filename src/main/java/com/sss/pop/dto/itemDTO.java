package com.sss.pop.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@Alias("item")
public class itemDTO {
    private int itemNum;        // 물품 번호
    private String itemName;    // 물품명
    private String itemContent; // 설명
    private int itemPrice;      // 가격
    private int itemRegion;     // 지역

    private MultipartFile itemPhoto;  // 물품 사진
    private String itemPhotoName;   // 물품 사진 이름
    private MultipartFile itemPhoto1;  // 물품1 사진
    private String itemPhoto1Name;   // 물품1 사진 이름
    private MultipartFile itemPhoto2;  // 물품2 사진
    private String itemPhoto2Name;   // 물품2 사진 이름
    private MultipartFile itemPhoto3;  // 물품3 사진
    private String itemPhoto3Name;   // 물품3 사진 이름

    private int itemCategory;   // 카테고리
    private String itemSeller;  // 판매자
    private int itemLike;       // 좋아요
    private int itemHit;        // 조회수
    private Date itemDate;      // 등록일
    private int itemCheck;      // 판매여부
    private String itemBuyer;   // 구매자

    private String itDate;      // itemList 페이지의 ~~ 전 출력시 사용
    private String itRegion;    // itemList 페이지의 판매지역 출력시 사용
}
