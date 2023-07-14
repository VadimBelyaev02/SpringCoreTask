package com.vadim.springcore.dto.response;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

@Builder
public class ApiResponseDto<T> {

    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant timestamp;
    private String message;

    private Color color;

    private T data;


    @RequiredArgsConstructor
    public enum Color {
        LIGHT("light"),
        SUCCESS("green"),
        ERROR("red");

        private final String color;

    }
}
