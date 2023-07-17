package com.vadim.springcore.model.dto.converter;

import com.vadim.springcore.model.entity.Tag;
import com.vadim.springcore.model.dto.request.TagRequestDto;
import com.vadim.springcore.model.dto.response.TagResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TagConverter {

    public Tag toEntity(TagRequestDto requestDto) {
        final String name = requestDto.getName();

        return Tag.builder()
                .name(name)
                .build();
    }

    public TagResponseDto toDto(Tag tag) {
        final String name = tag.getName();

        return TagResponseDto.builder()
                .name(name)
                .build();
    }
}
