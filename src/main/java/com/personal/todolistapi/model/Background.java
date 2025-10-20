package com.personal.todolistapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "background")
public class Background {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_Type")
    private String fileType;

    @Column(name = "file_Fomate")
    private String fileFomate;

    @Column(name = "file_Size")
    private double fileSize;

    @Column(name = "file_Name")
    private String fileName;

    @Column(name = "Part_Upload")
    private String partUpload;

    @ManyToOne
    @JoinColumn(name = "todo_list_id")
    private TodoList todoList;
}
