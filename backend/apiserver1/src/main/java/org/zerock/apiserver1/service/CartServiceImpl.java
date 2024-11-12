package org.zerock.apiserver1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.apiserver1.domain.Cart;
import org.zerock.apiserver1.domain.CartItem;
import org.zerock.apiserver1.domain.Member;
import org.zerock.apiserver1.domain.Product;
import org.zerock.apiserver1.dto.CartItemDTO;
import org.zerock.apiserver1.dto.CartItemListDTO;
import org.zerock.apiserver1.repository.CartItemRepository;
import org.zerock.apiserver1.repository.CartRepository;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CartServiceImpl implements  CartServcice {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public List<CartItemListDTO> addOrModify(CartItemDTO cartItemDTO) {
        //상품홈페이지에서 기존에 담겨있는 상품에서  장바구니 추가하는경우

        String email= cartItemDTO.getEmail();
        Long pno=cartItemDTO.getPno();
        int qty =cartItemDTO.getQty();
        Long cino=cartItemDTO.getCino();
        if(cino !=null){

            Optional<CartItem> cartItemResult = cartItemRepository.findById(cino);
        CartItem cartItem=cartItemResult.orElseThrow();

        cartItem.changeQty(qty);
        cartItemRepository.save(cartItem);
        return  getCartItems(email);


        }

        Cart cart=getCart(email); // 이메일로 장바구니 불러오고 없다면 생성하기
        //이후 카드안에 동일한 상품이 있는지
        CartItem cartItem=null;
         cartItem = cartItemRepository.getItemOfPno(email, pno);

        if(cartItem==null){
            Product product=Product.builder().pno(pno).build();
            cartItem=CartItem.builder().product(product).cart(cart).qty(qty).build();

        }else{
            cartItem.changeQty(qty);
        }
        cartItemRepository.save(cartItem);

        return  getCartItems(email);
    }

    private Cart getCart(String email) {

        Cart cart=null;
        Optional<Cart> result = cartRepository.getCartOfMember(email);
        if(result.isEmpty()){
            log.info("Cart of the member is not exist");
            Member member=Member.builder()
                    .email(email).build();

            Cart tempCart = Cart.builder().owner(member).build();
            cart = cartRepository.save(tempCart);

        } else{
            cart=result.get();
        }
        return cart;

    }

    @Override
    public List<CartItemListDTO> getCartItems(String email) {
        return cartItemRepository.getItemsOfCartDTOByEmail(email);
    }

    @Override
    public List<CartItemListDTO> remove(Long cino) {
        Long cno = cartItemRepository.getCartFromItem(cino);
        cartItemRepository.deleteById(cino);


        return cartItemRepository.getItemsOfCartDTOByCart(cno) ;
    }



}
