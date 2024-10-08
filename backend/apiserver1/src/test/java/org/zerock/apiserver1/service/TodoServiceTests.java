package org.zerock.apiserver1.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.apiserver1.dto.PageRequestDTO;
import org.zerock.apiserver1.dto.TodoDTO;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class TodoServiceTests {

@Autowired
    TodoService todoService;

@Test
    public void testGet(){
    Long tno=50L;

    log.info(todoService.get(tno));
}
@Test
    public void testRegister(){
    TodoDTO todoDTO=TodoDTO.builder()
            .title("Title...")
            .content("Content...")
            .dueDate(LocalDate.of(2023,12,31))
            .build();

    log.info(todoService.register(todoDTO));
}

@Test
    public void testGEtLIst(){
    PageRequestDTO pageRequestDTO=PageRequestDTO.builder().build();
    log.info(todoService.getlist(pageRequestDTO));
}

}
