package org.zerock.apiserver1.service;

import org.zerock.apiserver1.domain.Todo;
import org.zerock.apiserver1.dto.PageRequestDTO;
import org.zerock.apiserver1.dto.PageResponseDTO;
import org.zerock.apiserver1.dto.TodoDTO;

public interface TodoService {

    TodoDTO get(Long tno);

Long register(TodoDTO todoDTO);

void modify(TodoDTO todoDTO);
void remove(Long tno);

PageResponseDTO<TodoDTO> getlist(PageRequestDTO pageRequestDTO);

    default TodoDTO entityToDTO(Todo todo){
        return TodoDTO.builder()
                .tno(todo.getTno()).title(todo.getTitle()).content(todo.getContent()).complete(todo.isComplete()).dueDate(todo.getDueDate())


                .build();
    }
default Todo dtoToEntity(TodoDTO todoDTO){
        return Todo.builder()
                .tno(todoDTO.getTno())
                .title(todoDTO.getTitle())
                .content(todoDTO.getContent())
                .complete(todoDTO.isComplete())
                .dueDate(todoDTO.getDueDate())



                    .build();
}
}
