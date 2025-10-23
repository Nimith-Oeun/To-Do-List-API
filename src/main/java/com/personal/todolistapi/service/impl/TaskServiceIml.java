package com.personal.todolistapi.service.impl;

import com.personal.todolistapi.dto.request.TaskRequest;
import com.personal.todolistapi.dto.request.TaskUpdateRequest;
import com.personal.todolistapi.dto.respones.TaskResones;
import com.personal.todolistapi.exceptions.ResourNotFound;
import com.personal.todolistapi.mapper.TaskMapper;
import com.personal.todolistapi.model.Task;
import com.personal.todolistapi.repository.TaskRepository;
import com.personal.todolistapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceIml implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResones create(TaskRequest request) {

        Task mapToTask = taskMapper.mapToTask(request);
        Task savedTask = taskRepository.save(mapToTask);

        return taskMapper.mapToTaskResones(savedTask);
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
    public void delete(Long id) {
        Task task = getById(id);
        taskRepository.delete(task);
        log.info("Task with id: {} deleted successfully", id);
    }
}
