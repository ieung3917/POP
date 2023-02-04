package com.sss.pop.dao;

import com.sss.pop.dto.noDTO;
import com.sss.pop.dto.pageDTO;
import com.sss.pop.dto.quDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface quDAO {
    int quWrite(quDTO quest);

    int quCount(String where);

    List<quDTO> pQuList(pageDTO paging);

    quDTO quView(int quNum);

    int quDelete(int quNum);

    int quModify(quDTO quest);

    void quHit(int quNum);

    int quAWrite(quDTO quest);

    int quNACount(String where);

    List<quDTO> pQuNAList(pageDTO paging);

    int quACount(String where);

    List<quDTO> pQuAList(pageDTO paging);
}
