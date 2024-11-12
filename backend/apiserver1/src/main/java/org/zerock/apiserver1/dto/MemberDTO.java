package org.zerock.apiserver1.dto;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MemberDTO extends User {
    private String email, pw, nickname;

    private boolean social;
    private List<String> roleNames = new ArrayList<>();


    public MemberDTO(String email, String pw, String nickname, boolean social,
                     List<String> roleNames) {
        super(email, pw, roleNames.stream().map(str ->
                new SimpleGrantedAuthority("ROLE_" + str)).collect(Collectors.toList()));

//security가 원하는 타입에 맞게 dto를 만들었고  우리는 문자열 하지만 simpleGrantedAuthroity는 객채로 받아야함
        // 문자열로 권한을 만들어주는 기능을 썻다고 생각해라
        this.email = email;
        this.pw = pw;
        this.nickname = nickname;
        this.social = social;
        this.roleNames = roleNames;
    }

    //jwt 문자열 클래임을 만들어주는 기능

    public Map<String, Object> getClaims() {
        Map<String, Object> dataMap=new HashMap<>();
        dataMap.put("email", email);
        dataMap.put("pw",pw);
        dataMap.put("nickname", nickname);
        dataMap.put("social", social);
        dataMap.put("roleNames", roleNames);
        return dataMap;
    }
}