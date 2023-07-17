package com.vadim.springcore.model.dto.mapper;

import com.vadim.springcore.model.entity.GiftCertificate;
import com.vadim.springcore.model.dto.request.GiftCertificateRequestDto;
import com.vadim.springcore.model.dto.response.GiftCertificateResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
