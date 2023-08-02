package com.vadim.springtask.controller;

import com.vadim.springtask.model.dto.response.ApiResponseDto;
import com.vadim.springtask.model.dto.response.PageResponseDto;
import com.vadim.springtask.model.dto.response.TagResponseDto;
import com.vadim.springtask.model.dto.response.UserResponseDto;
import com.vadim.springtask.model.entity.User;
import com.vadim.springtask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.vadim.springtask.util.constants.PaginationConstants.DEFAULT_PAGE_NUMBER;
import static com.vadim.springtask.util.constants.PaginationConstants.DEFAULT_PAGE_SIZE;

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
    public ApiResponseDto<PageResponseDto<UserResponseDto>> getAllUsers(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        page = Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER);
        pageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);
        PageResponseDto<UserResponseDto> pageResponseDto = service.getAll(page, pageSize);

        return ApiResponseDto.successApiResponse(
                "All users; page: " + page + "; page size: " + pageSize,
                pageResponseDto
        );
    }
}
