package com.personal.todolistapi.dto.respones;

import lombok.Data;

import java.util.List;

@Data
public class TodolistRespones {

    private Long id;

    private String name;

    private String icon;

    private String color;

    private Integer background;

    private String uuid;

    private List<TaskResonesInsideList> tasks;
}