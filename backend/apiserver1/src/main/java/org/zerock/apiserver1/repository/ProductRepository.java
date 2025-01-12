package org.zerock.apiserver1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.apiserver1.domain.Product;
import org.zerock.apiserver1.repository.search.ProductSearch;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long>, ProductSearch {


    @Query("select p from Product p where p.pno= :pno")
    Optional<Product> selectOne(@Param("pno") Long pno);

@Modifying
    @Query("update Product p set p.delFlag=:delFlag where p.pno=:pno")
 void updateToDelete(@Param("pno") Long pno,@Param("delFlag") boolean flag);


@Query("select p,pi from Product p left join p.imageList pi where pi.ord= 0 and p.delFlag=false ")
Page<Object[]> selectList(Pageable pageable);
//pageable 1목록당 10개씩 뜨는데 이걸 일일이 쿼리를쓰면 너무 성능이 안나오니 쓴거임
    // Page<object[] 쓴 이유  Page는 페이징된 결과를 담는 스프링 데이터 JPA의 객체
}
