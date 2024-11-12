package org.zerock.apiserver1.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.zerock.apiserver1.domain.Member;
import org.zerock.apiserver1.domain.MemberRole;
import org.zerock.apiserver1.dto.MemberDTO;
import org.zerock.apiserver1.dto.MemberModifyDTO;
import org.zerock.apiserver1.repository.MemberRepository;

import java.util.LinkedHashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements  MemberService {

private final MemberRepository memberRepository;
private final PasswordEncoder passwordEncoder;
    @Override
    public MemberDTO getKakaoMember(String accessToken) {

    //accessToken을 이용하여 사용자 정도 가져오기
String nickname=getEmailFromKakaoAccessToken(accessToken);




        // 기존에 DB 회원정보가 있는경우 /없는경우
        Optional<Member> result=memberRepository.findById(nickname);
        if(result.isPresent()){

            MemberDTO memberDTO =entityToDTO(result.get());

            log.info("existed......"+memberDTO);
            return memberDTO;
        }
        Member socialMember=makeMember(nickname);
        memberRepository.save(socialMember);

        MemberDTO memberDTO=entityToDTO(socialMember);

        return memberDTO;
    }

    @Override
    public void modifyMember(MemberModifyDTO memberModifyDTO) {
        Optional<Member> result=memberRepository.findById(memberModifyDTO.getEmail());

        Member member=result.orElseThrow();
        member.changeNickname(memberModifyDTO.getNickname());
        member.changeSocial(false);
        member.changePw(passwordEncoder.encode(memberModifyDTO.getPw()));

        memberRepository.save(member);
    }

    private Member makeMember(String email){
        String tempPassword=makeTempPassword();
        log.info("tempPassword:"+tempPassword);

        Member member= Member.builder()
                .email(email)
                .pw(passwordEncoder.encode(tempPassword))
                .nickname("Social Member")
                .social(true)
                .build();

        member.addRole(MemberRole.USER);
        return member;
    }


    private String getEmailFromKakaoAccessToken(String accessToken){
        String kakaoGetUserURL="https://kapi.kakao.com/v2/user/me";
//카카오 사용자  정보 가져오기 참고함 메서드 , 요청 헤더(Authorization),content type
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers=new HttpHeaders();
        headers.add("Authorization","Bearer " + accessToken);
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<String> entity =new HttpEntity<>(headers);
        //HttpEntity는 요청에 필요한 헤더 정보만 포함하고 있고  이 객체는 나중에 RestTemplate을 통해 api 요청시 사용됨

        //이거 인제 요청 보내야함으로
        UriComponents uriBuilder=UriComponentsBuilder.fromHttpUrl(kakaoGetUserURL).build();
//UriComponentsBuilder는 URL을 동적으로 생성할 수 있도록 도와주는 스프링 클래스입니다.
//복잡한 URL을 작성할 때 파라미터나 경로, 쿼리 스트링을 안전하게 추가할 수 있는 유용한 도구입니다

        //데이터호출

        ResponseEntity<LinkedHashMap> response=
                restTemplate.exchange(uriBuilder.toString(), HttpMethod.GET,entity, LinkedHashMap.class);

        log.info(response);

log.info("---------------------------------------");
log.info(response);

LinkedHashMap<String,LinkedHashMap> bodyMap=response.getBody();
LinkedHashMap<String,String> kakaoAccount=bodyMap.get("properties");
String nickname=kakaoAccount.get("nickname");
log.info("nickname="+nickname);

return nickname;
    }



    private String makeTempPassword(){
        StringBuffer buffer=new StringBuffer();

        for(int i=0;i<10;i++){
            buffer.append((char)((int)(Math.random()*55)+65));
        }
        return buffer.toString();
    }
}


