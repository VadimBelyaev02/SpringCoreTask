package com.vadim.springcore.model.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDto<T> {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using = ToStringSerializer.class)
    private Instant timestamp;
    private String message;
    private T data;


    public static <T> ApiResponseDto<T> successApiResponse(String message, T data) {
        return ApiResponseDto.<T>builder()
                .timestamp(Instant.now())
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponseDto<T> badApiResponse(String message, T data) {
        return ApiResponseDto.<T>builder()
                .message(message)
                .timestamp(Instant.now())
                .data(data)
                .build();
    }

    public static <T> ApiResponseDto<T> badApiResponse(String message) {
        return badApiResponse(message, null);
    }
}
