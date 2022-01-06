package com.example.java_project.utils;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    // 1000: 요청 성공
    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    INVALID_JWT(false, 1001, "Invalid JWT"),

    // 2000: Request 오류
    TASKS_EMPTY_CONTENTS(false, 2000, "내용을 입력해주세요."),
    USER_EXIST_ID(false, 2001, "회원 아이디가 이미 존재합니다."),

    // 3000: Response 오류
    USER_LOGIN_FAIL(false, 3000, "아이디 또는 패스워드를 다시 확인해 주세요."),

    // 4000: Database, Server 오류
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    PASSWORD_ENCRYPTION_ERROR(false, 4001, "비밀번호 암호화에 실패하였습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
