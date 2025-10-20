package com.personal.todolistapi.service.impl;

import com.personal.todolistapi.dto.request.TodolistRequest;
import com.personal.todolistapi.dto.respones.TodolistRespones;
import com.personal.todolistapi.model.TodoList;
import com.personal.todolistapi.repository.TodoListRepository;
import com.personal.todolistapi.service.TodolistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodolistImpl implements TodolistService {

    private final TodoListRepository todoListRepository;

    @Override
    public TodolistRespones create(TodolistRequest request) {



        return null;
    }

    @Override
    public TodoList getById(Long id) {
        return null;
    }
}
