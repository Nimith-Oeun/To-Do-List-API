package com.personal.todolistapi.service;

import com.personal.todolistapi.dto.request.TaskRequest;
import com.personal.todolistapi.dto.request.TaskUpdateRequest;
import com.personal.todolistapi.dto.respones.TaskResones;
import com.personal.todolistapi.model.Task;

public interface TaskService {

    TaskResones create(TaskRequest request);

    Task getById(Long id);

    Task update(Long id ,TaskUpdateRequest request);

    void delete(Long id);

}
