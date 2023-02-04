package com.sss.pop.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Date;

@Data
@Alias("quest")
public class quDTO {
    private int quNum;          // 문의 일련번호
    private String quTitle;     // 제목
    private String quContent;   // 내용
    private String quWriter;    // 작성자
    private Date quDate;        // 작성일
    private int quHit;          // 조회수
    private String quAnswer;    // 답글
    private Date quADate;       // 답변일
}
