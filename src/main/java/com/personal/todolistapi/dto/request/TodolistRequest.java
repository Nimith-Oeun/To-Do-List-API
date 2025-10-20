package com.personal.todolistapi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TodolistRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("icon")
    private String icon;

    @JsonProperty("color")
    private String color;

    @JsonProperty("background")
    private Integer background;

    @JsonProperty("User_UUID")
    private String UUID;

    @JsonProperty("tasks")
    private Integer tasks;
}
