package com.example.java_project.src.task.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.lang.String;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Task")
public class TaskEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idx;

    @Column(nullable = false, length = 100)
    private String userIdx;

    @Column(nullable = false, length = 300)
    private String contents;

    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean done;
}
