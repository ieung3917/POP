package com.sss.pop.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("search")
public class seDTO {
    
    private String search; // 검색 내역
    
}
