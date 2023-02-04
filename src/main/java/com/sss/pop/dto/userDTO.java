package com.sss.pop.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@Alias("users")
public class userDTO {

    private String userId;          // 아이디
    private String userPw;          // 비밀번호
    private String userName;        // 이름
    private Date userBirth;         // 생년월일
    private String userGender;      // 성별
    private String userEmail;       // 이메일
    private String userAddr;        // 주소
    private int userCash;           // 캐시
    private int userGrade;       // 등급

    private String userProfileName;  // 프로필사진 이름

    private MultipartFile userProfile;  // 프로필사진


    private String addr1;       // 주소1
    private String addr2;       // 주소2
    private String addr3;       // 주소3
    
    private String userStoreIntro; // 상점 한줄소개

}
