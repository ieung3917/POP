package com.sss.pop.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Date;

@Data
@Alias("repque")
public class rqDTO {

    private int rqNum;              // 질문 일련번호
    private String rqTitle;         // 질문 제목
    private String rqContent;       // 질문 내용
    private int rqHit;              // 질문 조회수
    private Date rqDate;            // 작성일
    private String rqAnswer;

}
