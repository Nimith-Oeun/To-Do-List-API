package com.personal.todolistapi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaskRequest {

    @JsonProperty("title")
    private String title;

}
