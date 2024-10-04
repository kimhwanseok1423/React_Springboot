package org.zerock.apiserver1.repository.search;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.apiserver1.domain.QTodo;
import org.zerock.apiserver1.domain.Todo;
import org.zerock.apiserver1.dto.PageRequestDTO;

import java.util.List;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {
  private final JPAQueryFactory queryFactory;



    public TodoSearchImpl(EntityManager entityManager) {

        super(Todo.class);
        this.queryFactory=new JPAQueryFactory(entityManager);
    }




    @Override
    public Page<Todo> search1(PageRequestDTO pageRequestDTO ) {
QTodo todo= QTodo.todo;
        JPQLQuery<Todo> query=from(todo);
        log.info("search1...............");
        Pageable pageable= PageRequest.of(
                pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(),
                Sort.by("tno").descending());



this.getQuerydsl().applyPagination(pageable,query);
        List<Todo> list = query.fetch();
        long total = query.fetchCount();


        return new PageImpl<>(list,pageable,total);
    }



}
