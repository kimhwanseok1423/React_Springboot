package org.zerock.apiserver1.service;

import jakarta.transaction.Transactional;
import org.zerock.apiserver1.dto.CartItemDTO;
import org.zerock.apiserver1.dto.CartItemListDTO;

import java.util.List;

@Transactional
public interface CartServcice {

    List<CartItemListDTO> addOrModify(CartItemDTO cartItemDTO);
    List<CartItemListDTO> getCartItems(String email);
    List<CartItemListDTO> remove(Long cino);
}
