package com.vadim.springcore.dto.converter;

import com.vadim.springcore.dto.request.TagRequestDto;
import com.vadim.springcore.dto.response.TagResponseDto;
import com.vadim.springcore.entity.Tag;
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
