package com.personal.todolistapi.dto.respones;

import lombok.Data;

@Data
public class TodolistRespones {

    private Long id;

    private String name;

    private String icon;

    private String color;

    private Integer background;

    private String UUID;

    private Integer tasks;
}