package com.sss.pop.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("sell")
public class sellDTO {

    private int sellSeq;         // 판매내역 번호
    private String sellSeller;      // 판매자
    private String sellBuyer;       // 구매자
    private int sellNum;         // 판매물품 번호

}
