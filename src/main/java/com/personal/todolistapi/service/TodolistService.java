package com.personal.todolistapi.service;


import com.personal.todolistapi.dto.request.TodolistRequest;
import com.personal.todolistapi.dto.respones.TodolistRespones;
import com.personal.todolistapi.model.TodoList;

public interface TodolistService {

    TodolistRespones create(TodolistRequest request);
    TodoList getById(Long id);
}
