package com.sss.pop.dao;

import com.sss.pop.dto.noDTO;
import com.sss.pop.dto.pageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface noDAO {
    
    int noWrite(noDTO notice);

    int noCount(String where);

/*    List<noDTO> pNoList(pageDTO paging);*/

    noDTO noView(int noNum);

    int noDelete(int noNum);

    int noModify(noDTO notice);

    List<noDTO> pNoList(pageDTO paging);
}
