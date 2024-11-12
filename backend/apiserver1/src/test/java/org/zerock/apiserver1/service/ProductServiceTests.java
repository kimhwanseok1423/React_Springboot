package org.zerock.apiserver1.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.apiserver1.dto.PageRequestDTO;
import org.zerock.apiserver1.dto.PageResponseDTO;
import org.zerock.apiserver1.dto.ProductDTO;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@Log4j2

public class ProductServiceTests {

@Autowired
    private ProductService productService;

@Test
    public void testList(){
    PageRequestDTO pageRequestDTO= PageRequestDTO.builder().build();
    PageResponseDTO<ProductDTO> list = productService.getList(pageRequestDTO);
    log.info(list.getDtoList());


}
//@Test
//    public void testRegister(){
//    ProductDTO productDTO=ProductDTO.builder()
//            .pname("새로운 상품")
//            .pdesc("1111")
//            .price(10000)
//            .build();
//
//    productDTO.setUploadFileNames(
//            List.of(
//                    UUID.randomUUID()+"_"+"Test1.png",
//                    UUID.randomUUID()+"_"+"Test2.png"
//
//            )
//    );
//
//productService.register(productDTO);
//}
}
