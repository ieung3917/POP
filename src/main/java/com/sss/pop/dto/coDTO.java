package com.sss.pop.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("comments")
public class coDTO {
    private int coNum;          // 댓글 번호
    private int cbNum;          // 게시물 번호
    private String coContent;   // 내용
    private String coWriter;    // 작성자
    private Date coDate;        // 날짜

    private String comDate;
}
