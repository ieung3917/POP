package com.sss.pop.dao;

import com.sss.pop.dto.auDTO;
import com.sss.pop.dto.itemDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface mainDAO {
    List<auDTO> auIndex();

    List<itemDTO> itemIndex();
}
