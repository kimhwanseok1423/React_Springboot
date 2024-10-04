package org.zerock.apiserver1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.apiserver1.domain.Todo;
import org.zerock.apiserver1.repository.search.TodoSearch;

public interface TodoRepository extends JpaRepository<Todo,Long>, TodoSearch {
}
