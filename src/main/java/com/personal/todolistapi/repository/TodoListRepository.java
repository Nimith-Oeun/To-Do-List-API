package com.personal.todolistapi.repository;

import com.personal.todolistapi.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {


}
