package org.zerock.apiserver1.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.apiserver1.domain.Member;

public interface MemberRepository extends JpaRepository<Member,String> {

    @EntityGraph(attributePaths = {"memberRoleList"}) // memberRoleList도 가져오게 하기위해
    @Query("select m from Member m where m.email= :email")
    Member getWithRoles(@Param("email") String email);
}
