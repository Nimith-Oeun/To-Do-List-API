package com.personal.todolistapi.service;


import com.personal.todolistapi.dto.request.TodolistRequest;
import com.personal.todolistapi.dto.respones.TodolistRespones;
import com.personal.todolistapi.model.TodoList;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface TodolistService {

    TodolistRespones create(TodolistRequest request , Jwt jwt);
    TodoList getById(Long id);
    List<TodoList> getAll(Jwt jwt);
}
