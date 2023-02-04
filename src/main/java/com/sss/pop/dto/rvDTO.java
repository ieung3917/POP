package com.sss.pop.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("review")
public class rvDTO {

    private int rvNum;              // 물품 번호
    private String rvContent;       // 리뷰 내용
    private String rvWriter;        // 작성자
    private String rvDate;            // 작성일
    private int rvGrade;            // 평점
    private String rvSeller;        // 판매자 아이디
    private int rviNum;             // 상품 번호(외래키)

    private String userId;            // 상점 페이지 요청시 사용
    private String userProfileName;   // 상점 페이지 요청시 사용


}
