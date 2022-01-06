package com.example.java_project.src.user.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String idx;
    private String userId;
    private String userPassword;
    private String userName;
    private String jwt;
}
