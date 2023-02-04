package com.sss.pop.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("itemComments")
public class icDTO {
    private int icNum;          // 게시글 번호
    private int icItemNum;      // 물건 번호
    private String icWriter;    // 작성자
    private String icDate;        // 날짜

    private String icContent;   // 내용
    
    private String userProfileName; // 프로필사진
}
