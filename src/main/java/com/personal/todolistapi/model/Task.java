package com.personal.todolistapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.personal.todolistapi.enums.Priority;
import com.personal.todolistapi.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@Entity
@Table(name = "task")
@ToString(exclude = "todoList")
public class Task extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String Step;

    private String description;

    private Date dueDate;

    private Boolean isCompleted = false;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_list_id")
    @JsonBackReference
    private TodoList todoList;

    private String uuid;
}
