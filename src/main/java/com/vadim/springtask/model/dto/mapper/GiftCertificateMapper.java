package com.vadim.springtask.model.dto.mapper;

import com.vadim.springtask.model.dto.request.GiftCertificateRequestDto;
import com.vadim.springtask.model.dto.response.GiftCertificateResponseDto;
import com.vadim.springtask.model.entity.GiftCertificate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(uses = TagMapper.class)
public interface GiftCertificateMapper {

    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    GiftCertificate toEntity(GiftCertificateRequestDto dto);

    GiftCertificateResponseDto toResponseDto(GiftCertificate giftCertificate);

    @Mapping(target = "price", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "duration", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "description", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateGiftCertificateFromDto(GiftCertificateRequestDto requestDto, @MappingTarget GiftCertificate giftCertificate);
}
