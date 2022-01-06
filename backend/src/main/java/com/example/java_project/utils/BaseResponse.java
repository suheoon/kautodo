package com.example.java_project.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static com.example.java_project.utils.BaseResponseStatus.SUCCESS;

@Builder
@Getter
@AllArgsConstructor
// response 순서
@JsonPropertyOrder({"success", "code", "message", "result"})
public class BaseResponse<T> {
    @JsonProperty(value = "success")
    private final boolean isSuccess;
    @JsonProperty(value = "message")
    private final String message;
    @JsonProperty(value = "code")
    private final int code;
    @JsonProperty(value = "result")
    private T result;

    // 요청에 성공할 경우
    public BaseResponse(T result) {
        this.isSuccess = SUCCESS.isSuccess();
        this.message = SUCCESS.getMessage();
        this.code = SUCCESS.getCode();
        this.result = result;
    }

    // 요청에 실패할 경우
    public BaseResponse(BaseResponseStatus status) {
        this.isSuccess = status.isSuccess();
        this.message = status.getMessage();
        this.code = status.getCode();
    }
}
