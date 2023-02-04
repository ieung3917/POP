package com.sss.pop.dao;

import com.sss.pop.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface userDAO {
    userDTO session1();

    List<noteDTO> myNote(String userId);
    // idOverlapCheck : 아이디 중복 체크(ajax)
    String idOverlapCheck(String userId);

    // userJoin : 회원가입 처리
    int userJoin(userDTO user);

    // userLogin : 로그인 처리(쿠키 생성)
    userDTO userLogin(String userId);

    // 회원 상점 페이지 이동(아래 4개)
    // userInfo : 회원 정보(아이디, 등급, 프로필만)
    userDTO userInfo(String userId);

    // userAuList : 회원 경매 판매 목록
    List<auDTO> auList(String userId);

    // userRvList : 회원 상점 리뷰 목록
    List<rvDTO> userRvList(String userId);
    // userRvCnt : 회원 상점 리뷰 갯수
    int userRvCnt(String userId);


    userDTO userModiForm(String userId);

    int userModi(userDTO user);

    int userModiPw(userDTO user);

    void cashRecharge(userDTO user);


    /////////////////////////////////////////////////////////////////////////
    // 거래 목록 관련 //
    // regionOutput : 회원 거래 판매 목록 지역 출력
    reDTO regionOutput(int itemRegion);
    // userSellList : 회원 전체 거래 판매 목록
    List<itemDTO> itemAllList(String userId);

    // itemSellingList : 회원 판매중 거래 판매 목록
    List<itemDTO> itemSellingList(String userId);

    // itemDoneList : 회원 판매완료 거래 판매 목록
    List<itemDTO> itemDoneList(String userId);


    // 경매 목록 관련 //
    // auRegionOutput : 회원 거래 판매 목록 지역 출력
    reDTO auRegionOutput(int auRegion);

    // auSellingList : 판매중인 경매 목록
    List<auDTO> auSellingList(String userId);

    // auAllList : 전체 경매 목록
    List<auDTO> auAllList(String userId);

    // auDoneList : 판매 완료 경매 목록
    List<auDTO> auDoneList(String userId);

    // 모든 회원 조회
    List<String> userList();
    
    // 카카오 정보로 회원가입
    int userKakaoJoin(userDTO user);

    // 카카오 정보로 로그인
    userDTO userKakaoLogin(String id);



}
