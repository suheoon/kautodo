package com.example.java_project.src.user.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idx;

    @Column(nullable = false, length = 100)
    private String userId;

    @Column(nullable = false, length = 100)
    private String userPassword;

    @Column(nullable = false, length = 100)
    private String userName;
}
