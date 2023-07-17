package com.vadim.springcore.dto.mapper;

import com.vadim.springcore.dto.request.GiftCertificateRequestDto;
import com.vadim.springcore.dto.response.GiftCertificateResponseDto;
import com.vadim.springcore.entity.GiftCertificate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(uses = TagMapper.class)
public interface GiftCertificateMapper {

    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    GiftCertificate toEntity(GiftCertificateRequestDto dto);

    GiftCertificateResponseDto toResponseDto(GiftCertificate giftCertificate);

    void updateGiftCertificateFromDto(GiftCertificateRequestDto requestDto, @MappingTarget GiftCertificate giftCertificate);
}
