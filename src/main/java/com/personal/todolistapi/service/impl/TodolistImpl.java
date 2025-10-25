package com.personal.todolistapi.service.impl;

import com.personal.todolistapi.common.GetUserUUID;
import com.personal.todolistapi.dto.request.TodolistRequest;
import com.personal.todolistapi.dto.respones.TodolistRespones;
import com.personal.todolistapi.exceptions.ResourNotFound;
import com.personal.todolistapi.mapper.TodoListMapper;
import com.personal.todolistapi.model.Task;
import com.personal.todolistapi.model.TodoList;
import com.personal.todolistapi.repository.TaskRepository;
import com.personal.todolistapi.repository.TodoListRepository;
import com.personal.todolistapi.service.TodolistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodolistImpl implements TodolistService {

    private final TodoListRepository todoListRepository;
    private final TaskRepository taskRepository;
    private final TodoListMapper todoListMapper;

    @Override
    public TodolistRespones create(TodolistRequest request, Jwt jwt) {

        //get user uuid from token
        String userUuid = GetUserUUID.getUserUUID(jwt);

        if (userUuid.equals("null")) {
            throw new ResourNotFound("User UUID not found in token");
        }

        TodoList mappedTodoList = todoListMapper.mapToTodoList(request);
        mappedTodoList.setUuid(userUuid);

        // Ensure child relationship consistency
        if (mappedTodoList.getTasks() != null) {
            mappedTodoList.getTasks().forEach(task ->
                    task.setTodoList(mappedTodoList)
            );
        }

        TodoList savedTodoList = todoListRepository.save(mappedTodoList);

        return todoListMapper.mapToTodolistRespones(savedTodoList);

    }

    @Override
    public TodoList getById(Long id) {
        return todoListRepository.findById(id)
                .orElseThrow(() -> new ResourNotFound("Cannot find TodoList with id: " + id));
    }

    @Override
    public List<TodolistRespones> getAll(Jwt jwt) {

        String currentUserUUID = GetUserUUID.getUserUUID(jwt);
        List<TodoList> allByUuid = todoListRepository.findAllByUuid(currentUserUUID);

        if (currentUserUUID.equals("null")) {
            throw new ResourNotFound("User UUID not found in token");
        }

        if (allByUuid.isEmpty()) {
            throw new ResourNotFound("User UUID not found in database");
        }

        return allByUuid.stream()
                .map(TodoListMapper.INSTANCE::mapToTodolistRespones)
                .toList();
    }
}
