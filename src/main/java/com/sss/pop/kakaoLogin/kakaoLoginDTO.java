package com.sss.pop.kakaoLogin;

import lombok.Data;

@Data
public class kakaoLoginDTO {

    private String profile_nickname;    //닉네임
    private String profile_image;       //프로필 사진
    private String profile_image_name;  //프로필 사진명
    private String account_email;       //카카오계정(이메일)
    private String gender;              //성별
    private String birthday;            //생일


}
