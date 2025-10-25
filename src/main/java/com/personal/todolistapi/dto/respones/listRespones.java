package com.personal.todolistapi.dto.respones;

import lombok.Data;

import java.util.List;

@Data
public class listRespones {

    private Long id;

    private String name;

    private String icon;

    private String color;

    private Integer background;

    private String UUID;

    private List<TaskRespones> tasks;
}