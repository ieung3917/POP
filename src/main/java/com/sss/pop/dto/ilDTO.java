package com.sss.pop.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("itemLike")
public class ilDTO {
    private int ilNum;          // 게시글 번호
    private int ilItem;      // 물건 번호
    private String ilUser;    // 작성자
}
