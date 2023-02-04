package com.sss.pop.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("category")
public class caDTO {
    private int caSeq;      // 카테고리 시퀀스
    private String caMain;  // 카테고리(메인)
    private String caSub;   // 카테고리(서브)
}
