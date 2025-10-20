package com.personal.todolistapi.controller;

import com.personal.todolistapi.dto.request.TaskRequest;
import com.personal.todolistapi.exceptions.GlobalException;
import com.personal.todolistapi.exceptions.SuccessRespone;
import com.personal.todolistapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PreAuthorize("hasRole('role_user')")
    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody TaskRequest request) {
        return ResponseEntity.ok(
                SuccessRespone.success(
                        taskService.create(request)
                ));
    }
}
