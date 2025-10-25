package com.personal.todolistapi.dto.respones;

import com.personal.todolistapi.enums.Priority;
import com.personal.todolistapi.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;

@Data
public class TaskRespones {

    private Long id;

    private String title;

    private String Step;

    private String description;

    private Date dueDate;

    private Boolean isCompleted;

    private Priority priority;

    private Status status;

    private String listTitle;

}
