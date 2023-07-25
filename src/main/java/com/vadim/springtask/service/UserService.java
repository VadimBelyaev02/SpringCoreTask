package com.vadim.springtask.service;

import com.vadim.springtask.model.dto.response.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto getById(UUID id);

    List<UserResponseDto> getAll();
}
