package com.sss.pop.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("note")
public class noteDTO {
    private int noteSeq;        // 쪽지 시퀀스
    private String noteSender;  // 보내는 사람
    private String noteReceiver;// 받는 사람
    private String noteTitle;   // 제목
    private String noteContent; // 내용
    private String noteDate;      // 작성일
    private int noteCheck;      // 쪽지 확인 여부
    private int noteDelCheck;   // 쪽지 삭제 여부
}
