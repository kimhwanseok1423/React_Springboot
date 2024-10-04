package org.zerock.apiserver1.service;

import jakarta.transaction.Transactional;
import org.zerock.apiserver1.dto.PageRequestDTO;
import org.zerock.apiserver1.dto.PageResponseDTO;
import org.zerock.apiserver1.dto.ProductDTO;

@Transactional
public interface ProductService {

PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO);


    Long register(ProductDTO productDTO);

    ProductDTO get(Long pno);

    void modify(ProductDTO productDTO);
void remove(Long pno);
}
