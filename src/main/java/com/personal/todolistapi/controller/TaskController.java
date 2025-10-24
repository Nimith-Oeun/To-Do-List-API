package com.personal.todolistapi.controller;

import com.personal.todolistapi.dto.request.TaskRequest;
import com.personal.todolistapi.dto.request.TaskRequestDTO;
import com.personal.todolistapi.exceptions.SuccessRespone;
import com.personal.todolistapi.mapper.TaskMapper;
import com.personal.todolistapi.model.Task;
import com.personal.todolistapi.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasRole('role_user')")
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateTask(
            @PathVariable("id") Long id,
            @RequestBody TaskRequestDTO request,
            HttpServletRequest httpServletRequest
    ) {
        Task update = taskService.update(id, request);
        TaskMapper.INSTANCE.mapToTaskResones(update);
        return ResponseEntity.ok(
                SuccessRespone.success(update)
        );
    }
}
