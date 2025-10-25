package com.personal.todolistapi.service.impl;

import com.personal.todolistapi.dto.request.TaskRequest;
import com.personal.todolistapi.dto.request.TaskRequestDTO;
import com.personal.todolistapi.dto.request.TaskUpdateRequest;
import com.personal.todolistapi.dto.respones.TaskResones;
import com.personal.todolistapi.dto.respones.TaskRespones;
import com.personal.todolistapi.exceptions.ResourNotFound;
import com.personal.todolistapi.mapper.TaskMapper;
import com.personal.todolistapi.model.Task;
import com.personal.todolistapi.repository.TaskRepository;
import com.personal.todolistapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceIml implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskRespones create(TaskRequestDTO request) {

        /**
            * Get user uuid from security context
         */
        JwtAuthenticationToken authentication =
                (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        Jwt token = authentication.getToken();

        String userUuid = token.getClaimAsString("sub");

        if (userUuid.equals("null")) {
            throw new ResourNotFound("User UUID not found in token");
        }

        Task mapToTask = taskMapper.mapToTask(request);
        mapToTask.setUuid(userUuid);

        Task savedTask = taskRepository.save(mapToTask);

        return taskMapper.mapToTaskRespones(savedTask);
    }

    @Override
    public Task getById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(()-> new ResourNotFound("Task not found with id: " + id));
    }

    @Override
    public Task update(
            Long id ,
            TaskUpdateRequest request
    ) {

        Task task = getById(id);

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setIsCompleted(request.getIsCompleted());
        task.setDueDate(request.getDueDate());
        task.setPriority(request.getPriority());
        task.setStatus(request.getStatus());
        task.setStep(request.getStep());

        return taskRepository.save(task);
    }

    @Override
    public List<TaskRespones> getAllTask() {
        return taskRepository.findAll().stream()
                .map(taskMapper::mapToTaskRespones)
                .toList();
    }

    @Override
    public TaskResones createTaskByListId(Long listId, TaskRequest request) {

        Optional<Task> todoList = taskRepository.findByTodoList(listId);

        if (todoList.isEmpty()) {
            throw new ResourNotFound("Task not found with id: " + listId);
        }

        Task createdTask = new Task();

        createdTask.setTitle(request.getTitle());

        Task savedTask = taskRepository.save(createdTask);

        return   TaskMapper.INSTANCE.mapToTaskResones(savedTask)
;
    }

    @Override
    public void delete(Long id) {
        Task task = getById(id);
        taskRepository.delete(task);
        log.info("Task with id: {} deleted successfully", id);
    }
}
