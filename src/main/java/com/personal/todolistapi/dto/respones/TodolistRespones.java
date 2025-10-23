package com.personal.todolistapi.dto.respones;

import com.personal.todolistapi.model.Background;
import com.personal.todolistapi.model.Task;
import lombok.Data;

import java.util.List;

@Data
public class TodolistRespones {

    private Long id;

    private String name;

    private String icon;

    private String color;

    private Integer background;

    private String UUID;

    private List<TaskResones> tasks;
}