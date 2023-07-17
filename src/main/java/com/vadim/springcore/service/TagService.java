package com.vadim.springcore.service;

import com.vadim.springcore.model.dto.request.TagRequestDto;
import com.vadim.springcore.model.dto.response.TagResponseDto;

import java.util.List;
import java.util.UUID;

public interface TagService {

    TagResponseDto getById(UUID id);

    List<TagResponseDto> getAll();

    TagResponseDto save(TagRequestDto requestDto);

    TagResponseDto update(UUID id, TagRequestDto requestDto);

    void deleteById(UUID id);
}
