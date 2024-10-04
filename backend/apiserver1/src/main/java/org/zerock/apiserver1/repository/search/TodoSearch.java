package org.zerock.apiserver1.repository.search;

import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.zerock.apiserver1.domain.Todo;
import org.zerock.apiserver1.dto.PageRequestDTO;

public interface TodoSearch {

    Page<Todo> search1(PageRequestDTO pageRequestDTO);
}
