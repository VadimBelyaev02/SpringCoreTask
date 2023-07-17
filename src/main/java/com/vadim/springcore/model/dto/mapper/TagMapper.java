package com.vadim.springcore.model.dto.mapper;

import com.vadim.springcore.model.dto.request.GiftCertificateRequestDto;
import com.vadim.springcore.model.dto.request.TagRequestDto;
import com.vadim.springcore.model.dto.response.TagResponseDto;
import com.vadim.springcore.model.entity.GiftCertificate;
import com.vadim.springcore.model.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface TagMapper {

    Tag toEntity(TagRequestDto dto);

    TagResponseDto toResponseDto(Tag tag);

    List<Tag> tagRequestDtoListToTagList(List<TagRequestDto> tagRequestDtos);

    void updateTagFromDto(TagRequestDto requestDto, @MappingTarget Tag tag);

}
