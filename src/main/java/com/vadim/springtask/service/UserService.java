package com.vadim.springtask.service;

import com.vadim.springtask.model.dto.response.PageResponseDto;
import com.vadim.springtask.model.dto.response.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto getById(UUID id);

    PageResponseDto<UserResponseDto> getAll(Integer page, Integer pageSize);
}
