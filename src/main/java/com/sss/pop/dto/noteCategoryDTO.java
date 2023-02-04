package com.sss.pop.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("noteCategory")
public class noteCategoryDTO {

    private String category;    // 검색 카테고리
    private String keyword;     // 검색 키워드

}
