package com.personal.todolistapi.mapper;

import com.personal.todolistapi.dto.request.TaskRequestDTO;
import com.personal.todolistapi.dto.respones.TaskResonesInsideList;
import com.personal.todolistapi.dto.respones.TaskRespones;
import com.personal.todolistapi.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);


   Task mapToTask(TaskRequestDTO request);

   TaskRespones mapToTaskRespones(Task task);

   TaskResonesInsideList mapToTaskResones (Task task);
}
