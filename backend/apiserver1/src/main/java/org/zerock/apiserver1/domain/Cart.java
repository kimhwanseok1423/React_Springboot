package org.zerock.apiserver1.dto;

import jakarta.persistence.*;
import org.zerock.apiserver1.domain.Member;


public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;
@OneToOne
@JoinColumn(name = "member_owner")
    private Member owner;
}
