package com.sss.pop.dao;

import com.sss.pop.dto.pageDTO;
import com.sss.pop.dto.rqDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface rqDAO {
    int rqWrite(rqDTO repque);

    int rqCount(String where);

    List<rqDTO> pRqList(pageDTO paging);

    rqDTO rqView(int rqNum);

    int rqDelete(int rqNum);

    int rqModify(rqDTO repque);

    void rqHit(int rqNum);

}
