package com.personal.todolistapi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.personal.todolistapi.enums.Priority;
import com.personal.todolistapi.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;

@Data
public class TaskRequestDTO {

    @JsonProperty("title")
    private String title;

    @JsonProperty("Step")
    private String Step;

    @JsonProperty("description")
    private String description;

    @JsonProperty("dueDate")
    private Date dueDate;

    @JsonProperty("isCompleted")
    private Boolean isCompleted;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

}
