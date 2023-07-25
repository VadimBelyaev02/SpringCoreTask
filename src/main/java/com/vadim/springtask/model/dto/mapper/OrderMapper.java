package com.vadim.springtask.model.dto.mapper;

import com.vadim.springtask.model.dto.response.OrderResponseDto;
import com.vadim.springtask.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    OrderResponseDto toResponseDto(Order order);
}

