package org.zerock.apiserver1.repository.search;

import org.zerock.apiserver1.dto.PageRequestDTO;
import org.zerock.apiserver1.dto.PageResponseDTO;
import org.zerock.apiserver1.dto.ProductDTO;

public interface ProductSearch {

    PageResponseDTO<ProductDTO> searchList(PageRequestDTO pageRequestDTO);


}
