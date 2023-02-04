package com.sss.pop.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@Alias("board")
public class boDTO {
    private int boNum;          // 게시판 번호
    private String boTitle;     // 글 제목
    private String boContent;   // 내용
    private String boWriter;    // 작성자
    private Date boDate;        // 날짜
    private int boHit;

    private MultipartFile boFile;   // 파일
    private String boFileName;      // 파일 이름
}
