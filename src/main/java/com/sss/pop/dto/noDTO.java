package com.sss.pop.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Date;

@Data
@Alias("notice")
public class noDTO {
    private int noNum;          // 공지번호
    private String noTitle;     // 제목
    private String noContent;   // 내용
    private String noWriter;    // 작성자
    private Date noDate;        // 작성일
}
