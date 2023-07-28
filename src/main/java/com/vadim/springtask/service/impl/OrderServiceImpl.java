package com.vadim.springtask.service.impl;

import com.vadim.springtask.dao.OrderDao;
import com.vadim.springtask.exception.NotFoundException;
import com.vadim.springtask.model.dto.mapper.OrderMapper;
import com.vadim.springtask.model.dto.request.OrderRequestDto;
import com.vadim.springtask.model.dto.response.OrderResponseDto;
import com.vadim.springtask.model.entity.Order;
import com.vadim.springtask.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.vadim.springtask.util.constants.OrderConstants.ORDER_NOT_FOUND_BY_ID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao dao;
    private final OrderMapper mapper;

    @Override
    public OrderResponseDto getById(UUID id) {
        Order order = dao.findById(id).orElseThrow(() ->
                new NotFoundException(String.format(ORDER_NOT_FOUND_BY_ID, id))
        );
        return mapper.toResponseDto(order);
    }

    @Override
    public OrderResponseDto save(OrderRequestDto requestDto) {
        return null;
    }

    @Override
    public List<OrderResponseDto> getAllByUserId(UUID id) {
        return dao.findAllByUserId(id).stream()
                .map(mapper::toResponseDto)
                .toList();
    }
}
