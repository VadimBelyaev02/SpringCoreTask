package com.vadim.springcore.service.impl;

import com.vadim.springcore.dao.GiftCertificateTagDao;
import com.vadim.springcore.dao.TagDao;
import com.vadim.springcore.exception.DuplicateRecordException;
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

import static com.vadim.springcore.utils.constants.TagConstants.TAG_ALREADY_EXISTS_WITH_NAME;
import static com.vadim.springcore.utils.constants.TagConstants.TAG_NOT_FOUND_BY_ID;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagDao dao;
    private final TagMapper mapper;
    private final GiftCertificateTagDao giftCertificateTagDao;

    @Override
    @Transactional(readOnly = true)
    public TagResponseDto getById(UUID id) {
        Tag tag = dao.findById(id).orElseThrow(() ->
                new NotFoundException(String.format(TAG_NOT_FOUND_BY_ID, id))
        );
        return mapper.toResponseDto(tag);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TagResponseDto> getAll() {
        return dao.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TagResponseDto save(TagRequestDto requestDto) {
        if (dao.existsByName(requestDto.getName())) {
            throw new DuplicateRecordException(String.format(TAG_ALREADY_EXISTS_WITH_NAME, requestDto.getName()));
        }
        return mapper.toResponseDto(dao.save(mapper.toEntity(requestDto)));
    }

    @Override
    @Transactional
    public TagResponseDto update(UUID id, TagRequestDto requestDto) {
        if (dao.existsByName(requestDto.getName())) {
            throw new DuplicateRecordException(String.format(TAG_ALREADY_EXISTS_WITH_NAME, requestDto.getName()));
        }
        Tag tag = dao.findById(id).orElseThrow(() ->
                new NotFoundException(String.format(TAG_NOT_FOUND_BY_ID, id))
        );
        mapper.updateTagFromDto(requestDto, tag);
        return mapper.toResponseDto(dao.save(tag));
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        if (!dao.existsById(id)) {
            throw new NotFoundException(String.format(TAG_NOT_FOUND_BY_ID, id));
        }
        giftCertificateTagDao.deleteByTagId(id);
        dao.deleteById(id);
    }
}
