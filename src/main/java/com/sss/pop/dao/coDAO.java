package com.sss.pop.dao;

import com.sss.pop.dto.coDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface coDAO {
    List<coDTO> coList(int cbNum);

    int coWrite(coDTO comments);

    int coDelete(coDTO comments);

    int coModify(coDTO comments);
}
