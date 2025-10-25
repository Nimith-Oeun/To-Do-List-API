package com.personal.todolistapi.mapper;

import com.personal.todolistapi.dto.request.TodolistRequest;
import com.personal.todolistapi.dto.respones.TodolistRespones;
import com.personal.todolistapi.model.TodoList;
import com.personal.todolistapi.service.BackroundServervice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" ,
        uses = { BackroundServervice.class})
public interface TodoListMapper {

    TodoListMapper INSTANCE = Mappers.getMapper(TodoListMapper.class);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "background", source = "backgroundId")
    TodoList mapToTodoList(TodolistRequest request);

    @Mapping(target = "background", source = "background.id")
    @Mapping(target = "uuid", source = "uuid")
    TodolistRespones mapToTodolistRespones(TodoList todoList);


}
