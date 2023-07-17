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
    private Color color;
    private T data;
    private Integer status;


    @RequiredArgsConstructor
    public enum Color {
        LIGHT("light"),
        SUCCESS("green"),
        ERROR("red");

        private final String color;
    }

    public static <T> ApiResponseDto<T> successApiResponse(String message, T data) {
        return ApiResponseDto.<T>builder()
                .color(Color.SUCCESS)
                .timestamp(Instant.now())
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponseDto<T> badApiResponse(String message) {
        return ApiResponseDto.<T>builder()
                .message(message)
                .color(Color.ERROR)
                .timestamp(Instant.now())
                .build();
    }
}
