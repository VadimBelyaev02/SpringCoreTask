package com.vadim.springtask.service.impl;

import com.vadim.springtask.dao.GiftCertificateDao;
import com.vadim.springtask.dao.OrderDao;
import com.vadim.springtask.dao.UserDao;
import com.vadim.springtask.exception.NotFoundException;
import com.vadim.springtask.model.dto.mapper.OrderMapper;
import com.vadim.springtask.model.dto.request.OrderRequestDto;
import com.vadim.springtask.model.dto.response.OrderResponseDto;
import com.vadim.springtask.model.entity.GiftCertificate;
import com.vadim.springtask.model.entity.Order;
import com.vadim.springtask.model.entity.User;
import com.vadim.springtask.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static com.vadim.springtask.util.constants.GiftCertificateConstants.GIFT_CERTIFICATE_NOT_FOUND_BY_ID;
import static com.vadim.springtask.util.constants.OrderConstants.ORDER_NOT_FOUND_BY_ID;
import static com.vadim.springtask.util.constants.UserConstants.USER_NOT_FOUND_BY_ID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao dao;
    private final OrderMapper mapper;
    private final UserDao userDao;
    private final GiftCertificateDao giftCertificateDao;

    @Override
    @Transactional(readOnly = true)
    public OrderResponseDto getById(UUID id) {
        Order order = dao.findById(id).orElseThrow(() ->
                new NotFoundException(String.format(ORDER_NOT_FOUND_BY_ID, id))
        );
        return mapper.toResponseDto(order);
    }

    @Override
    @Transactional
    public OrderResponseDto save(OrderRequestDto requestDto) {
        User user = userDao.findById(requestDto.getUserId()).orElseThrow(
                () -> new NotFoundException(String.format(USER_NOT_FOUND_BY_ID, requestDto.getUserId()))
        );
        GiftCertificate giftCertificate = giftCertificateDao.findById(requestDto.getGiftCertificateId()).orElseThrow(
                () -> new NotFoundException(String.format(GIFT_CERTIFICATE_NOT_FOUND_BY_ID, requestDto.getGiftCertificateId()))
        );
        Order order = Order.builder()
                .cost(giftCertificate.getPrice())
                .purchaseTime(Instant.now())
                .user(user)
                .giftCertificate(giftCertificate)
                .build();
        return mapper.toResponseDto(dao.save(order));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDto> getAllByUserId(UUID id) {
        return dao.findAllByUserId(id).stream()
                .map(mapper::toResponseDto)
                .toList();
    }
}
