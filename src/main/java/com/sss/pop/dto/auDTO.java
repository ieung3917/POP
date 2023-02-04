package com.sss.pop.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;


import java.sql.Date;


@Data
@Alias("auction")
public class auDTO {
    private int auNum;        // 물품 번호
    private String auName;    // 물품명
    
    private String auContent; // 설명
    private int auPrice;      // 가격
    private int auRegion;     // 지역

    private MultipartFile auPhoto;  // 물품 사진
    private String auPhotoName;   // 물품 사진 이름

    private int auCategory;   // 카테고리
    private String auSeller;  // 판매자
    private int auLike;       // 좋아요
    private int auHit;        // 조회수
    private Date auDate;      // 등록일
    private String aDate;


    private Date auDeadline;  // 경매마감일
    private String aucDate;


    private int auCheck;      // 판매여부
    private String auBuyer;   // 구매자

    private int auDeadlineDay;          // 마감시간(일)
    private int auDeadlineHour;         // 마감시간(시간)
    private int auDeadlineMinute;       // 마감시간(분)

    private int auDeadlineDate;         // 마감시간(일+시간+분)

    private String aucRegion;    // itemList 페이지의 판매지역 출력시 사용



}
