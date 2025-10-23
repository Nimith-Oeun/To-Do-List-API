package com.personal.todolistapi.repository;

import com.personal.todolistapi.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {

    Optional<TodoList> findByUuid(String uuid);

}
