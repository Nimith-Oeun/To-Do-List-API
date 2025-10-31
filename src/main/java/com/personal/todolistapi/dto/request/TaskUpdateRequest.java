package com.personal.todolistapi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.personal.todolistapi.enums.Priority;
import com.personal.todolistapi.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class TaskUpdateRequest {

    @JsonProperty("title")
    @NotBlank(
            message = "Title must not be blank"
    )
    private String title;

    @JsonProperty("step")
    private String Step;

    @JsonProperty("description")
    private String description;

    @JsonProperty("due_date")
    private Date dueDate;

    @JsonProperty("is_completed")
    private Boolean isCompleted;

    @JsonProperty("priority")
    private Priority priority;

    @JsonProperty("status")
    private Status status;

}
