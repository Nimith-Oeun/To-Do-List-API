package com.personal.todolistapi.service.impl;

import com.personal.todolistapi.dto.request.TaskRequestDTO;
import com.personal.todolistapi.dto.request.TaskUpdateRequest;
import com.personal.todolistapi.dto.respones.TaskRespones;
import com.personal.todolistapi.exceptions.BadRequestException;
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
                .map(resppones -> {
                    TaskRespones respone = TaskMapper.INSTANCE.mapToTaskRespones(resppones);
                    respone.setListTitle(
                            resppones.getTodoList().getName()
                    );
                    return respone;
                })
                .toList();
    }

    @Override
    public Task updateIsCompleted(Long id , TaskUpdateRequest request) {

        Task task = getById(id);

        Boolean isCompleted = request.getIsCompleted();
        if (isCompleted == null) {
            throw new BadRequestException("isCompleted field cannot be null");
        }

        task.setIsCompleted(isCompleted);

        return taskRepository.save(task);
    }

    @Override
    public void delete(Long id) {
        Task task = getById(id);
        taskRepository.delete(task);
        log.info("Task with id: {} deleted successfully", id);
    }
}
