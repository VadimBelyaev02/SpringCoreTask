package com.vadim.springtask.service;

import com.vadim.springtask.model.dto.request.OrderRequestDto;
import com.vadim.springtask.model.dto.response.OrderResponseDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderResponseDto getById(UUID id);

//    List<OrderResponseDto> getAll();

    OrderResponseDto save(OrderRequestDto requestDto);
//
//    OrderResponseDto update(OrderRequestDto requestDto);
//
//    void deleteById(UUID id);

    List<OrderResponseDto> getAllByUserId(UUID id);
}
