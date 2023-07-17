package com.vadim.springcore.model.dto.mapper;

import com.vadim.springcore.model.dto.request.TagRequestDto;
import com.vadim.springcore.model.dto.response.TagResponseDto;
import com.vadim.springcore.model.entity.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {

    Tag toEntity(TagRequestDto dto);

    TagResponseDto toResponseDto(Tag tag);

    List<Tag> tagRequestDtoListToTagList(List<TagRequestDto> tagRequestDtos);
}
