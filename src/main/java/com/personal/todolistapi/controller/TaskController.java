package com.personal.todolistapi.controller;

import com.personal.todolistapi.dto.request.TaskRequest;
import com.personal.todolistapi.dto.request.TaskRequestDTO;
import com.personal.todolistapi.dto.request.TaskUpdateRequest;
import com.personal.todolistapi.exceptions.SuccessRespone;
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
    public ResponseEntity<?> createTask(
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
            @PathVariable Long id ,
            @RequestBody TaskUpdateRequest request ,
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok(
                SuccessRespone.success(
                        taskService.update(id , request),
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
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok(
                SuccessRespone.success(
                        taskService.getAllTask(),
                        httpServletRequest
                ));
    }

    @PreAuthorize("hasRole('role_user')")
    @PostMapping("/createByListId")
    public ResponseEntity<?> createTaskByListId(
            @RequestParam Long listId ,
            @RequestBody TaskRequest request ,
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok(
                SuccessRespone.success(
                        taskService.createTaskByListId(listId , request),
                        httpServletRequest
                ));
    }

}
