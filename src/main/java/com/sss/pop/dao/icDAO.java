package com.sss.pop.dao;

import com.sss.pop.dto.icDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface icDAO {

    int itemCommentWrite(icDTO ic);

    List<icDTO> itemCommentList(int itemNum);

    void itemCommentDelete(int icNum);
}
