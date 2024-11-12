package org.zerock.apiserver1.repository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.zerock.apiserver1.domain.Cart;
import org.zerock.apiserver1.domain.CartItem;
import org.zerock.apiserver1.domain.Member;
import org.zerock.apiserver1.domain.Product;
import org.zerock.apiserver1.dto.CartItemListDTO;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class CartRepositoryTests {
    @Autowired
    private CartRepository cartRepository;
@Autowired
    private CartItemRepository cartItemRepository;

@Test
@Transactional
@Commit
public void testInsertByProduct(){
    String email="user1@aaa.com";
    Long pno=6L;
    int qty=3;

    //이메일 상품번호로 장바구니 아이템이 있는지  없으면 추가하는식으로 변경해서 저장

    CartItem cartItem = cartItemRepository.getItemOfPno(email, pno);
    if(cartItem !=null){
        cartItem.changeQty(qty);
        cartItemRepository.save(cartItem);
    }

    Optional<Cart> result = cartRepository.getCartOfMember(email);
    //이메일주소가지고 장바구니 카드 가져오기
    Cart cart=null;

    if(result.isEmpty()){
        //없다면 카드를 만들어야함

        Member member = Member.builder()
                .email(email).build();
        Cart tempCart = Cart.builder()
                .owner(member)
                .build();

    cart=cartRepository.save(tempCart);


    }else{ // 장바구니는 있으나 해당 상품의 장바구니 아이템은 없는경우
        cart= result.get();
    }
    Product product= Product.builder().pno(pno).build();
        cartItem = CartItem.builder().cart(cart).product(product).qty(qty).build();

cartItemRepository.save(cartItem);

}



@Test
    public void testListOfMember(){
    String email="user1@aaa.com";

    List<CartItemListDTO> cartItemListDTOList = cartItemRepository.getItemsOfCartDTOByEmail(email);

    for (CartItemListDTO dto:cartItemListDTOList
         ) {
        log.info(dto);

    }


}

@Test
    public void testDeleteThenList(){
    Long cino=1L;
    Long cno=cartItemRepository.getCartFromItem(cino);
    cartItemRepository.deleteById(cno);
    List<CartItemListDTO> cartItemList = cartItemRepository.getItemsOfCartDTOByCart(cno);
    for (CartItemListDTO dto: cartItemList
         ) {
        log.info(dto);

    }
}


}
