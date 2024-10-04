package org.zerock.apiserver1.repository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.apiserver1.domain.Product;
import org.zerock.apiserver1.dto.PageRequestDTO;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Log4j2
class ProductRepositoryTest {
@Autowired
    private ProductRepository productRepository;


@Test
    public void testInsert(){

for(int i=0;i<10;i++){

    Product product=Product.builder().pname(i+"Test").pdesc(i+"Test DESC").price(1000+i).build();
    product.addImageString(UUID.randomUUID()+"_"+i+"IMAGE1.png");
    product.addImageString(UUID.randomUUID()+"_"+i+"IMAGE2.png");

    productRepository.save(product);
}

}
@Transactional
@Commit
@Test
    public void testDelete(){
    Long pno=2L;
    productRepository.updateToDelete(2L,true);

}

@Test
    public void testUpdate(){
    Product product = productRepository.selectOne(1L).get();
    product.changePrice(30000);
    product.clearList();

    product.addImageString(UUID.randomUUID()+"_"+"PIMAGE1.png");
    product.addImageString(UUID.randomUUID()+"_"+"PIMAGE2.png");
    product.addImageString(UUID.randomUUID()+"_"+"PIMAGE3.png");
productRepository.save(product);

}

@Test
    public void testList(){
    Pageable pageable= PageRequest.of(0,10, Sort.by("pno").descending());
    Page<Object[]> result=productRepository.selectList(pageable);
    result.getContent().forEach(arr -> log.info(Arrays.toString(arr)));

}

@Test
    public void testSearch(){
    PageRequestDTO pageRequestDTO=PageRequestDTO.builder().build();
    productRepository.searchList(pageRequestDTO);
}
}