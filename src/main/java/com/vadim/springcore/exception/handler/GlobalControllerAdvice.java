package com.vadim.springcore.exception.handler;

import com.vadim.springcore.exception.DuplicateRecordException;
import com.vadim.springcore.exception.NotFoundException;
import com.vadim.springcore.model.dto.response.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        final ApiResponseDto<?> apiResponseDto = ApiResponseDto.badApiResponse(
                exception.getMessage(),
                exception.getBody()
        );
        return new ResponseEntity<>(apiResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponseDto<?>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        final ApiResponseDto<?> apiResponseDto = ApiResponseDto.badApiResponse(
                exception.getMessage()
        );
        return new ResponseEntity<>(apiResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponseDto<?>> handleNotFoundException(NotFoundException exception) {
        final ApiResponseDto<?> apiResponseDto = ApiResponseDto.badApiResponse(
                exception.getMessage()
        );
        return new ResponseEntity<>(apiResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({DuplicateRecordException.class})
    public ResponseEntity<ApiResponseDto<?>> handleDuplicateRecordException(DuplicateRecordException exception) {
        final ApiResponseDto<?> apiResponseDto = ApiResponseDto.badApiResponse(
                exception.getMessage()
        );
        return new ResponseEntity<>(apiResponseDto, HttpStatus.CONFLICT);
    }
}
