package com.vadim.springcore.service.impl;

import com.vadim.springcore.dao.TagDao;
import com.vadim.springcore.exception.NotFoundException;
import com.vadim.springcore.model.dto.mapper.TagMapper;
import com.vadim.springcore.model.dto.request.TagRequestDto;
import com.vadim.springcore.model.dto.response.TagResponseDto;
import com.vadim.springcore.model.entity.Tag;
import com.vadim.springcore.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.vadim.springcore.utils.constants.TagConstants.TAG_NOT_FOUND_BY_ID;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagDao dao;
    private final TagMapper mapper;

    @Override
    public TagResponseDto getById(UUID id) {
        Tag tag = dao.findById(id).orElseThrow(() ->
                new NotFoundException(String.format(TAG_NOT_FOUND_BY_ID, id))
        );
        return mapper.toResponseDto(tag);
    }

    @Override
    public List<TagResponseDto> getAll() {
        return dao.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TagResponseDto save(TagRequestDto requestDto) {

        return mapper.toResponseDto(dao.save(mapper.toEntity(requestDto)));
    }

    @Override
    @Transactional
    public TagResponseDto update(UUID id, TagRequestDto requestDto) {
        Tag tag = dao.findById(id).orElseThrow(() ->
                new NotFoundException(String.format(TAG_NOT_FOUND_BY_ID, id))
        );
        mapper.updateTagFromDto(requestDto, tag);
        // check if name already exists
        return mapper.toResponseDto(dao.save(tag));
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        if (!dao.existsById(id)) {
            throw new NotFoundException(String.format(TAG_NOT_FOUND_BY_ID, id));
        }
        dao.deleteById(id);
    }
}
