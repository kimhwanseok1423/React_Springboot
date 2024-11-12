package org.zerock.apiserver1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.zerock.apiserver1.dto.CartItemDTO;
import org.zerock.apiserver1.dto.CartItemListDTO;
import org.zerock.apiserver1.service.CartServcice;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/cart")
public class CartController {

    private final CartServcice cartServcice;
    @PreAuthorize("#cartItemDTO.email==authentication.name")
    //CartItemDTO의 이메일과 preauthorize에서의 이메일이 같게하는방법
    // 즉 지금 로그인한 사용자와 carItemDTO에서의 이메일이 같아야지만 처리됨
@PostMapping("/change")
    public List<CartItemListDTO> changeCart(@RequestBody CartItemDTO cartItemDTO){

        log.info(cartItemDTO);

        if(cartItemDTO.getQty()<=0){
            return cartServcice.remove(cartItemDTO.getCino());
        }
        return cartServcice.addOrModify(cartItemDTO);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
@GetMapping("/items")
    public List<CartItemListDTO> getCartItems(Principal principal){

        String email=principal.getName();
        log.info("email"+email);
        return cartServcice.getCartItems(email);
    }
}
