package org.zerock.apiserver1.repository.search;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.apiserver1.domain.Product;
import org.zerock.apiserver1.domain.QProduct;
import org.zerock.apiserver1.domain.QProductImage;
import org.zerock.apiserver1.dto.PageRequestDTO;
import org.zerock.apiserver1.dto.PageResponseDTO;
import org.zerock.apiserver1.dto.ProductDTO;

import java.util.List;
import java.util.Objects;

@Log4j2

public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {
private final JPAQueryFactory queryFactory;

       public ProductSearchImpl(EntityManager em){

           super(Product.class);
           this.queryFactory =new JPAQueryFactory(em);
       }



// super(Product.class)는 부모 클래스(QuerydslRepositorySupport)의 생성자를 호출하는 코드입니다.
// 여기서 **Product.class**는 QuerydslRepositorySupport에게 어떤 엔티티를 기반으로 작업할지를 명시하는 역할을 합니다.
    @Override
    public PageResponseDTO<ProductDTO> searchList(PageRequestDTO pageRequestDTO) {
       log.info("------------seachList------------------------");

        Pageable pageable= PageRequest.of(pageRequestDTO.getPage()-1, pageRequestDTO.getSize(),
                Sort.by("pno").descending());

        QProduct product=QProduct.product;
        QProductImage productImage=QProductImage.productImage;

//        JPQLQuery<Product> query=from(product);
//        query.leftJoin(product.imageList,productImage);
//        query.where(productImage.ord.eq(0));
//        Objects.requireNonNull(getQuerydsl()).applyPagination(pageable,query);
//        List<Tuple> productList=query.select(product,productImage).fetch();
//        long count=query.fetchCount();
//        log.info("=======================");
//        log.info(productList);

List<Tuple> productList=queryFactory.select(product,productImage).from(product)
        .leftJoin(product.imageList,productImage).where(productImage.ord.eq(0)).orderBy(product.pno.desc())
        .offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
long count=queryFactory.selectFrom(product).fetchCount();
       log.info("=======================");
       log.info(productList);


           return null;
    }
}
