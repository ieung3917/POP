package com.sss.pop.kakaoLogin;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sss.pop.dao.userDAO;
import com.sss.pop.dto.userDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class kakaoLoginServiceImpl implements kakaoLoginService{

    public ModelAndView mav;

    private final userDAO userdao;

    private final HttpSession session;



    // 카카오 인가 코드 전달 후 액세스 토큰 반환
    @Override
    public String getAccessToken(String code) {

        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=0d9934d491f5f82afbca7b5269823ea7");               // TODO REST_API_KEY 입력
            sb.append("&redirect_uri=http://localhost:9090/kakaoLogin/callback");   // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
//            System.out.println("responseCode 확인 : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
//            System.out.println("response body 확인 : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

//            System.out.println("access_token 확인 : " + access_Token);
//            System.out.println("refresh_token 확인 : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }


    // 액세스 토큰 값으로 카카오 회원 정보 조회
    public ModelAndView createKakaoUser(String token) throws Exception {
        mav = new ModelAndView();

        String reqURL = "https://kapi.kakao.com/v2/user/me";

        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
//            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
//            System.out.println("response body : " + result);


            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);


            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            
            
            // 회원 정보를 담기위한 객체 생성
            userDTO user = new userDTO();

            // JSON 객체에 담긴 회원 정보 추출
            long id = element.getAsJsonObject().get("id").getAsLong();      // 고유한 번호(아이디로 사용)

            // 기존에 있는 모든 회원 아이디 조회
            List<String> userIdList = userdao.userList();


            boolean var = true;
            // 모든 회원의 아이디와 비교
            for(String userId : userIdList){
                // 1. 같은 아이디가 있다면 로그인
                if(userId.equals("kakao_"+id)){
                    var = false;

                    userDTO kakaoLogin = userdao.userKakaoLogin("kakao_"+id);

                    session.setAttribute("login", kakaoLogin);
                    session.setAttribute("AccessToken", token);

                    session.setMaxInactiveInterval(60*60*24*7);    // session 유효 기간 1주일 설정
                    
                    mav.setViewName("redirect:/");
                }
            }

            // 2. 모든 아이디 조회후 존재하는 아이디가 없다면 카카오 정보로 회원가입
            if(var){
                String nickname = properties.getAsJsonObject().get("nickname").getAsString();   // 닉네임(이름으로 사용)
                String gender = kakao_account.getAsJsonObject().get("gender").getAsString();    // 성별(male,female)

                // 이메일
                boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
                String email = "";
                if(hasEmail){
                    email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
                }

                // 성별
                if(gender != null){
                    if(gender.equals("male")){
                        gender = "남자";
                    } else if(gender.equals("female")){
                        gender = "여자";
                    }
                }

                // 프로필 사진(일단 고정 시켜둠) (임시)
                // 파일 이름 설정
                String originalFileName = "checkin.png";

                // 난수 생성하기
                String uuid = UUID.randomUUID().toString().substring(0, 8);

                // 업로드 할 파일이름 생성하기(3번 + 2번)
                String userProfileName = uuid + "_" + originalFileName;

                // 파일 선택시 userDTO user객체의 userProfileName 필드에 업로드파일 이름 저장
                user.setUserProfileName(userProfileName);
                // (임시)

                user.setUserId("kakao_"+id);
                user.setUserName(nickname);
                user.setUserGender(gender);
                user.setUserEmail(email);
                
                // 카카오 회원 정보로 회원가입
                int kakaoJoinResult = userdao.userKakaoJoin(user);

                if(kakaoJoinResult > 0){
                    userDTO kakaoLogin2 = userdao.userKakaoLogin(user.getUserId());

                    session.setAttribute("login", kakaoLogin2);
                    mav.setViewName("redirect:/");
                } else {
                    mav.setViewName("redirect:/userLoginForm");
                }

            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return mav;
    }





}
