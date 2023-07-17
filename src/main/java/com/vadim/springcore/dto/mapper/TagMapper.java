package com.vadim.springcore.dto.mapper;

import com.vadim.springcore.dto.request.TagRequestDto;
import com.vadim.springcore.dto.response.TagResponseDto;
import com.vadim.springcore.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TagMapper {

    Tag toEntity(TagRequestDto dto);

    TagResponseDto toResponseDto(Tag tag);

    List<Tag> tagRequestDtoListToTagList(List<TagRequestDto> tagRequestDtos);
}
