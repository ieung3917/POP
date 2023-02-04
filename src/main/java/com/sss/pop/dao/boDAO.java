package com.sss.pop.dao;

import com.sss.pop.dto.boDTO;
import com.sss.pop.dto.pageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface boDAO {

    // boWrite : 게시물 작성
    int boWrite(boDTO board);

    // boCount : 게시물 개수
    int boCount(String where);

    // boList : 게시물 목록
    List<boDTO> boList(pageDTO paging);

    // boHit : 게시물 조회수 증가
    void boHit(int boNum);

    // boView : 게시물 상세보기
    boDTO boView(int boNum);

    // boModify : 게시물 수정
    int boModify(boDTO board);

    // boDelete : 게시물 삭제
    int boDelete(int boNum);


}
