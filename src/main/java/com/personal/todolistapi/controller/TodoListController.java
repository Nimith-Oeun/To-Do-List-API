package com.personal.todolistapi.controller;

import com.personal.todolistapi.dto.request.TodolistRequest;
import com.personal.todolistapi.dto.respones.TodolistRespones;
import com.personal.todolistapi.exceptions.SuccessRespone;
import com.personal.todolistapi.mapper.TodoListMapper;
import com.personal.todolistapi.model.TodoList;
import com.personal.todolistapi.service.TodolistService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todolists")
@RequiredArgsConstructor
public class TodoListController {

    private final TodolistService todolistService;

    @PreAuthorize("hasRole('role_user')")
    @PostMapping("/create")
    public ResponseEntity<?> createTodoList(
            @RequestBody TodolistRequest request ,
            @AuthenticationPrincipal Jwt jwt ,
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok(
                SuccessRespone.success(todolistService.create(request , jwt) ,httpServletRequest )
        );
    }


    @PreAuthorize("hasRole('role_user')")
    @GetMapping("/getList")
    public ResponseEntity<?> getTodoList(@AuthenticationPrincipal Jwt jwt ,
                                         HttpServletRequest httpServletRequest
    ) {

        return ResponseEntity.ok(
                SuccessRespone.success(
                        todolistService.getAll(jwt) ,
                        httpServletRequest
                )
        );
    }

    @PreAuthorize("hasRole('role_user')")
    @PostMapping("/createTask/{id}")
    public ResponseEntity<?> createTaskInTodoList(
            @PathVariable Long id ,
            @RequestBody TodolistRequest request ,
            HttpServletRequest httpServletRequest
    ) {
        TodoList todoList = todolistService.createTast(id , request);
        TodolistRespones respones = TodoListMapper.INSTANCE.mapToTodolistRespones(todoList);
        return ResponseEntity.ok(
                SuccessRespone.success(
                        respones ,
                        httpServletRequest
                )
        );
    }



}
