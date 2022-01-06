package com.example.java_project.utils;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends Exception{
    private BaseResponseStatus status;
}
