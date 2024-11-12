package org.zerock.apiserver1.service;

import jakarta.transaction.Transactional;
import org.zerock.apiserver1.domain.Member;
import org.zerock.apiserver1.dto.MemberDTO;
import org.zerock.apiserver1.dto.MemberModifyDTO;

import java.util.stream.Collectors;

@Transactional
public interface MemberService {

MemberDTO getKakaoMember(String accessToken);


void modifyMember(MemberModifyDTO memberModifyDTO);
    default MemberDTO entityToDTO(Member member){
        MemberDTO dto=new MemberDTO(
                member.getEmail()
                ,member.getPw()
                ,member.getNickname()
                , member.isSocial(),
                member.getMemberRoleList().stream().map(memberRole -> memberRole.name())
                        .collect(Collectors.toList()));


        return dto;
    }


}
