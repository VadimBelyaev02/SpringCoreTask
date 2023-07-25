package com.vadim.springtask.service.impl;

import com.vadim.springtask.dao.UserDao;
import com.vadim.springtask.exception.NotFoundException;
import com.vadim.springtask.model.dto.mapper.UserMapper;
import com.vadim.springtask.model.dto.response.UserResponseDto;
import com.vadim.springtask.model.entity.User;
import com.vadim.springtask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.vadim.springtask.util.constants.UserConstants.USER_NOT_FOUND_BY_ID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao dao;
    private final UserMapper mapper;

    @Override
    public UserResponseDto getById(UUID id) {
        User user = dao.findById(id).orElseThrow(() ->
               new NotFoundException(String.format(USER_NOT_FOUND_BY_ID, id))
        );
        return mapper.toResponseDto(user);
    }

    @Override
    public List<UserResponseDto> getAll() {
        return dao.findAll().stream()
                .map(mapper::toResponseDto)
                .toList();
    }
}
