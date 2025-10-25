package com.personal.todolistapi.service;

import com.personal.todolistapi.dto.request.TaskRequestDTO;
import com.personal.todolistapi.dto.request.TaskUpdateRequest;
import com.personal.todolistapi.dto.respones.TaskRespones;
import com.personal.todolistapi.model.Task;

import java.util.List;

public interface TaskService {

    TaskRespones create(TaskRequestDTO request);

    Task getById(Long id);

    Task update(Long id ,TaskUpdateRequest request);

    List<TaskRespones> getAllTask();

    Task updateIsCompleted(Long id , TaskUpdateRequest request);

    void delete(Long id);

}
