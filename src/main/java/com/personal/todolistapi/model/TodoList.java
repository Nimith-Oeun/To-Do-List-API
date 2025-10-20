package com.personal.todolistapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "todo_list")
public class TodoList extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String icon;

    private String color;

    private String uuid;

    private Integer background ;

    @OneToMany(
            mappedBy = "todoList",
            cascade = CascadeType.ALL
    )
    private List<Task> tasks = new ArrayList<>();
}
