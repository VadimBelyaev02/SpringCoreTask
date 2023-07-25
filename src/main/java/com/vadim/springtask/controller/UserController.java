package com.vadim.springtask.controller;

import com.vadim.springtask.model.dto.response.ApiResponseDto;
import com.vadim.springtask.model.dto.response.TagResponseDto;
import com.vadim.springtask.model.dto.response.UserResponseDto;
import com.vadim.springtask.model.entity.User;
import com.vadim.springtask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<UserResponseDto> getUser(@PathVariable("id")UUID id) {
        UserResponseDto userResponseDto = service.getById(id);

        return ApiResponseDto.successApiResponse(
                "User with id = " + id,
                userResponseDto
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> userResponseDtos = service.getAll();

        return ApiResponseDto.successApiResponse(
                "All users",
                userResponseDtos
        );
    }
}
