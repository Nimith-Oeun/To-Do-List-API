package com.personal.todolistapi.service.impl;

import com.personal.todolistapi.common.GetUserUUID;
import com.personal.todolistapi.dto.request.TodolistRequest;
import com.personal.todolistapi.dto.respones.TodolistRespones;
import com.personal.todolistapi.exceptions.ResourNotFound;
import com.personal.todolistapi.mapper.TodoListMapper;
import com.personal.todolistapi.model.TodoList;
import com.personal.todolistapi.repository.TodoListRepository;
import com.personal.todolistapi.service.TodolistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodolistImpl implements TodolistService {

    private final TodoListRepository todoListRepository;
    private final TodoListMapper todoListMapper;

    @Override
    public TodolistRespones create(TodolistRequest request, Jwt jwt) {

        //get user uuid from token
        String userUuid = GetUserUUID.getUserUUID(jwt);

        TodoList mappedTodoList = todoListMapper.mapToTodoList(request);
        mappedTodoList.setUuid(userUuid);

        TodoList savedTodoList = todoListRepository.save(mappedTodoList);

        return todoListMapper.mapToTodolistRespones(savedTodoList);
    }

    @Override
    public TodoList getById(Long id) {
        return todoListRepository.findById(id)
                .orElseThrow(() -> new ResourNotFound("Cannot find TodoList with id: " + id));
    }
}
