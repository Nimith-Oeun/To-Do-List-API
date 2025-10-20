package com.personal.todolistapi.model;

import com.personal.todolistapi.enums.Priority;
import com.personal.todolistapi.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "task")
public class Task extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String Step;

    private String description;

    private Date dueDate;

    private Boolean isCompleted;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_list_id")
    private TodoList todoList;
}
