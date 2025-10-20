package com.personal.todolistapi.mapper;

import com.personal.todolistapi.dto.request.TodolistRequest;
import com.personal.todolistapi.dto.respones.TodolistRespones;
import com.personal.todolistapi.model.TodoList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TodoListMapper {
    TodoListMapper INSTANCE = Mappers.getMapper(TodoListMapper.class);

    @Mapping(target = "uuid", ignore = true)
    TodoList mapToTodoList(TodolistRequest request);

    TodolistRespones mapToTodolistRespones(TodoList todoList);

}
