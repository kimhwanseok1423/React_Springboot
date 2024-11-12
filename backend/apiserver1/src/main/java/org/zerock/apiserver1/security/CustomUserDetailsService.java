package org.zerock.apiserver1.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.apiserver1.domain.Member;
import org.zerock.apiserver1.dto.MemberDTO;
import org.zerock.apiserver1.repository.MemberRepository;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       log.info("----------------loadUserbyname------------"+username);
        Member member=memberRepository.getWithRoles(username);

        if(member==null){
            throw new UsernameNotFoundException("Not Found");

        }
        MemberDTO memberDTO=new MemberDTO(
                member.getEmail(),
                member.getPw(),
                member.getNickname(),
                member.isSocial(),
                member.getMemberRoleList().stream()
                        .map(memberRole->memberRole.name()).collect(Collectors.toList()));
        log.info(memberDTO);
        return memberDTO;


//로그인을 처리하는 애임
     // loadUserByUsername ->id//UserDetails ->리턴타입은 우리한테는 memberDTO


    }
}
