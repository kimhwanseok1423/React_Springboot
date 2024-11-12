package org.zerock.apiserver1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.apiserver1.domain.Cart;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {



@Query("select m from Cart m where m.owner.email = :email")
Optional<Cart> getCartOfMember(@Param("email") String email);
//이메일 주소가지고 장바구니 카드를 가져온다.

}
