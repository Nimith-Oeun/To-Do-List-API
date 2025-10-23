package com.personal.todolistapi.service.impl;

import com.personal.todolistapi.dto.request.TaskRequest;
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
}
