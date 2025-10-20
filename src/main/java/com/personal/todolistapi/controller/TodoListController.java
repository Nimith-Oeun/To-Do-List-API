package com.personal.todolistapi.controller;

import com.personal.todolistapi.dto.request.TodolistRequest;
import com.personal.todolistapi.exceptions.SuccessRespone;
import com.personal.todolistapi.service.TodolistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todolists")
@RequiredArgsConstructor
public class TodoListController {

    private final TodolistService todolistService;

    @PostMapping("/create")
    public ResponseEntity<?> createTodoList(@RequestBody TodolistRequest request , Jwt jwt) {
        return ResponseEntity.ok(
                SuccessRespone.success(todolistService.create(request , jwt))
        );
    }
}
