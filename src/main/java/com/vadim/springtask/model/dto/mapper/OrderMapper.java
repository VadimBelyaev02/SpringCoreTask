package com.vadim.springtask.model.dto.mapper;

import com.vadim.springtask.model.dto.request.OrderRequestDto;
import com.vadim.springtask.model.dto.response.OrderResponseDto;
import com.vadim.springtask.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserMapper.class, GiftCertificateMapper.class})
public interface OrderMapper {

 //   @Mapping(target = "userId", expression = "java(order.getUser().getId())")
  //  @Mapping(target = "giftCertificateId", expression = "java(order.getGiftCertificate().getId())")
    OrderResponseDto toResponseDto(Order order);
}

