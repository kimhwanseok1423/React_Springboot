package org.zerock.apiserver1.security.handler;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.zerock.apiserver1.dto.MemberDTO;
import org.zerock.apiserver1.util.JWTUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Log4j2
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //로그인에 성공했으면 인증정보
        log.info("---------");
        log.info(authentication);
        log.info("----------");


        //성공했다고 가정했을떄

        MemberDTO memberDTO=(MemberDTO) authentication.getPrincipal();
        Map<String,Object> claims=memberDTO.getClaims();

//        claims.put("accessToken","");
//        claims.put("refreshToken","");

        String accessToken= JWTUtil.generateToken(claims,10); // 엑세스 토큰은 지금당장 사용할수있는 권리
        String refreshToken=JWTUtil.generateToken(claims,60*24); //교환권이라고 생각하기
        claims.put("accessToken",accessToken);
        claims.put("refreshToken",refreshToken);

// map에 넣은clains 을 json 문자열로 바꾸기

        Gson gson=new Gson();
        String jsonStr=gson.toJson(claims);
        response.setContentType("application/json; charset=UTF-8");

        PrintWriter printWriter=response.getWriter();
        printWriter.println(jsonStr);
        printWriter.close();

    }
}
