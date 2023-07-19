package com.vadim.springcore.model.dto.mapper;

import com.vadim.springcore.model.dto.request.GiftCertificateRequestDto;
import com.vadim.springcore.model.dto.request.TagRequestDto;
import com.vadim.springcore.model.dto.response.TagResponseDto;
import com.vadim.springcore.model.entity.GiftCertificate;
import com.vadim.springcore.model.entity.Tag;
import org.mapstruct.*;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TagMapper {

    Tag toEntity(TagRequestDto dto);

    TagResponseDto toResponseDto(Tag tag);

    List<Tag> tagRequestDtoListToTagList(List<TagRequestDto> tagRequestDtos);

    List<TagResponseDto> tagListToTagResponseDtoList(List<Tag> tags);
 //   @Mapping(target = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTagFromDto(TagRequestDto requestDto, @MappingTarget Tag tag);

}
