package com.sss.pop.service;

import com.sss.pop.dto.icDTO;

import java.util.List;

public interface icService {

    // 중고품 댓글 작성
    List<icDTO> itemCommentWrite(int icItemNum, String icContent, String icWriter);

    List<icDTO> itemCommentList(int itemNum);

    List<icDTO> itemCommentDelete(int icNum, int icItemNum);
}
