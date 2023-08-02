package com.vadim.springtask.controller;

import com.vadim.springtask.model.dto.request.OrderRequestDto;
import com.vadim.springtask.model.dto.response.ApiResponseDto;
import com.vadim.springtask.model.dto.response.OrderResponseDto;
import com.vadim.springtask.model.dto.response.TagResponseDto;
import com.vadim.springtask.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<List<OrderResponseDto>> getOrdersByUserId(@PathVariable("userId") UUID id) {
        List<OrderResponseDto> orderResponseDtos = service.getAllByUserId(id);
        return ApiResponseDto.successApiResponse(
                "All orders by user id = " + id,
                orderResponseDtos
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<OrderResponseDto> getOrder(@PathVariable("id") UUID id) {
        OrderResponseDto orderResponseDto = service.getById(id);

        return ApiResponseDto.successApiResponse(
                "Order with id = " + id,
                orderResponseDto
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponseDto<OrderResponseDto> postOrder(@RequestBody @Valid OrderRequestDto requestDto) {
        OrderResponseDto responseDto = service.save(requestDto);

        return ApiResponseDto.successApiResponse(
                "Order is created",
                responseDto
        );
    }
}
