package com.personal.todolistapi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.personal.todolistapi.model.Background;
import com.personal.todolistapi.model.Task;
import lombok.Data;

import java.util.List;

@Data
public class TodolistRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("icon")
    private String icon;

    @JsonProperty("color")
    private String color;

    @JsonProperty("background")
    private Integer backgroundId;

    @JsonProperty("tasks")
    private List<Task> tasks;
}
