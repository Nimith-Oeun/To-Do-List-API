package com.personal.todolistapi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.personal.todolistapi.enums.Priority;
import com.personal.todolistapi.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class TaskRequestDTO {

    @JsonProperty("title")
    @NotBlank(
            message = "Title must not be blank"
    )
    private String title;

    @JsonProperty("Step")
    private String Step;

    @JsonProperty("description")
    private String description;

    @JsonProperty("dueDate")
    private Date dueDate;

    @JsonProperty("isCompleted")
    private Boolean isCompleted;

    @JsonProperty("priority")
    private Priority priority;

    @JsonProperty("status")
    private Status status;

}
