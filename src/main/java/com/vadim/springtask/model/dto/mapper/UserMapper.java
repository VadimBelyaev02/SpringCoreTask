package com.vadim.springtask.model.dto.mapper;

import com.vadim.springtask.model.dto.request.TagRequestDto;
import com.vadim.springtask.model.dto.response.TagResponseDto;
import com.vadim.springtask.model.dto.response.UserResponseDto;
import com.vadim.springtask.model.entity.Tag;
import com.vadim.springtask.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserResponseDto toResponseDto(User user);

}
