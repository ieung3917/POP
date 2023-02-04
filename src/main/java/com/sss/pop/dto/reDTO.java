package com.sss.pop.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("region")
public class reDTO {

    private int reSeq;          // 지역 번호
    private String reCity;      // 지역(시)
    private String reNine;      // 지역(구)

}
