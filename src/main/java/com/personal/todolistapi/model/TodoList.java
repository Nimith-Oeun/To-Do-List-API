package com.personal.todolistapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "todo_list")
@ToString(exclude = "tasks")
public class TodoList extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String icon;

    private String color;

    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    private Background background ;

    @OneToMany(
            mappedBy = "todoList",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<Task> tasks = new ArrayList<>();
}
