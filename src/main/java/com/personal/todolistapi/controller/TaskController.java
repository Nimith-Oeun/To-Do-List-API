package com.personal.todolistapi.controller;

import com.personal.todolistapi.dto.request.TaskRequestDTO;
import com.personal.todolistapi.dto.request.TaskUpdateRequest;
import com.personal.todolistapi.dto.respones.TaskRespones;
import com.personal.todolistapi.exceptions.SuccessRespone;
import com.personal.todolistapi.mapper.TaskMapper;
import com.personal.todolistapi.model.Task;
import com.personal.todolistapi.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PreAuthorize("hasRole('role_user')")
    @PostMapping("/create")
    public ResponseEntity<?> createTask(
            @Valid
            @RequestBody TaskRequestDTO request ,
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok(
                SuccessRespone.success(
                        taskService.create(request),
                        httpServletRequest
                ));
    }

    @PreAuthorize("hasRole('role_user')")
    @PutMapping("/udate/{id}")
    public ResponseEntity<?> updateTask(
            @Valid
            @PathVariable Long id ,
            @RequestBody TaskUpdateRequest request ,
            HttpServletRequest httpServletRequest
    ) {
        Task update = taskService.update(id, request);
        TaskRespones respones = TaskMapper.INSTANCE.mapToTaskRespones(update);
        return ResponseEntity.ok(
                SuccessRespone.success(
                        respones ,
                        httpServletRequest
                ));
    }

    @PreAuthorize("hasRole('role_user')")
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(
            @PathVariable Long id ,
            HttpServletRequest httpServletRequest
    ) {
        taskService.delete(id);
        return ResponseEntity.ok(
                SuccessRespone.success(
                        "Delete task successfully" ,
                        httpServletRequest
                ));
    }

    @PreAuthorize("hasRole('role_user')")
    @GetMapping("/getAllTask")
    public ResponseEntity<?> getAllTask(
            @AuthenticationPrincipal Jwt jwt ,
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok(
                SuccessRespone.success(
                        taskService.getAllTask(jwt),
                        httpServletRequest
                ));
    }

    @PreAuthorize("hasRole('role_user')")
    @PatchMapping("/updateIsCompleted/{id}")
    public ResponseEntity<?> updateIsCompleted(
            @PathVariable Long id ,
            @RequestBody TaskUpdateRequest request ,
            HttpServletRequest httpServletRequest
    ) {

        taskService.updateIsCompleted(id , request);

        return ResponseEntity.ok(
                SuccessRespone.success(
                        "Update successfully" ,
                        httpServletRequest
                ));
    }



}
