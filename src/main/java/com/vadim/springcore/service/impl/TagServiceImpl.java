package com.vadim.springcore.service.impl;

import com.vadim.springcore.dto.request.TagRequestDto;
import com.vadim.springcore.dto.response.TagResponseDto;
import com.vadim.springcore.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TagServiceImpl implements TagService {
    @Override
    public TagResponseDto getById(UUID id) {
        return null;
    }

    @Override
    public List<TagResponseDto> getAll() {
        return null;
    }

    @Override
    public TagResponseDto save(TagRequestDto requestDto) {
        return null;
    }

    @Override
    public TagResponseDto update(TagRequestDto requestDto) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }
}
