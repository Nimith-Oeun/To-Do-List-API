package com.personal.todolistapi.mapper;

import com.personal.todolistapi.dto.request.TaskRequest;
import com.personal.todolistapi.dto.respones.TaskResones;
import com.personal.todolistapi.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);


   Task mapToTask(TaskRequest request);

   TaskResones mapToTaskResones(Task task);
}
