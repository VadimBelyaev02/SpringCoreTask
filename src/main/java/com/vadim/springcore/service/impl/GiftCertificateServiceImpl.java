package com.vadim.springcore.service.impl;

import com.vadim.springcore.criteria.GiftCertificateCriteria;
import com.vadim.springcore.dao.GiftCertificateDao;
import com.vadim.springcore.dao.GiftCertificateTagDao;
import com.vadim.springcore.dao.TagDao;
import com.vadim.springcore.dto.mapper.GiftCertificateMapper;
import com.vadim.springcore.dto.request.GiftCertificateRequestDto;
import com.vadim.springcore.dto.response.GiftCertificateResponseDto;
import com.vadim.springcore.entity.GiftCertificate;
import com.vadim.springcore.entity.GiftCertificateTag;
import com.vadim.springcore.entity.GiftCertificateTagId;
import com.vadim.springcore.entity.Tag;
import com.vadim.springcore.exception.NotFoundException;
import com.vadim.springcore.service.GiftCertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.vadim.springcore.utils.constants.GiftCertificateConstants.GIFT_CERTIFICATE_NOT_FOUND_BY_ID;

@Service
@RequiredArgsConstructor
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDao giftCertificateDao;
    private final GiftCertificateTagDao giftCertificateTagDao;
    private final GiftCertificateMapper mapper;
    private final TagDao tagDao;

    @Override
    public GiftCertificateResponseDto getById(UUID id) {
        GiftCertificate giftCertificate = giftCertificateDao.findById(id).orElseThrow(() ->
                new NotFoundException(String.format(GIFT_CERTIFICATE_NOT_FOUND_BY_ID, id))
        );
        return mapper.toResponseDto(giftCertificate);
    }

    @Override
    public List<GiftCertificateResponseDto> getAll() {
        return giftCertificateDao.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public GiftCertificateResponseDto save(GiftCertificateRequestDto requestDto) {
        GiftCertificate giftCertificate = mapper.toEntity(requestDto);
        giftCertificate.setCreateDate(Instant.now());
        giftCertificate.setLastUpdateDate(Instant.now());

        final GiftCertificate savedGiftCertificate = giftCertificateDao.save(giftCertificate);

        saveTags(giftCertificate.getTags(), savedGiftCertificate.getId());
//        giftCertificate.getTags().forEach(
//                tag -> {
//                    tag = tagDao.saveIfNotExists(tag);
//                    giftCertificateTagDao.save(new GiftCertificateTag(new GiftCertificateTagId(savedGiftCertificate.getId(), tag.getId())));
//                }
//        );
        return mapper.toResponseDto(giftCertificateDao.save(giftCertificate));
    }

    private void saveTags(List<Tag> tags, UUID giftCertificateId) {
        tags.forEach(
                tag -> {
                    tag = tagDao.saveIfNotExists(tag);
                    giftCertificateTagDao.save(new GiftCertificateTag(new GiftCertificateTagId(giftCertificateId, tag.getId())));
                }
        );

    }
    @Override
    public GiftCertificateResponseDto update(UUID id, GiftCertificateRequestDto requestDto) {
        GiftCertificate giftCertificate = giftCertificateDao.findById(id).orElseThrow(() ->
                new NotFoundException(String.format(GIFT_CERTIFICATE_NOT_FOUND_BY_ID, id))
        );
        mapper.updateGiftCertificateFromDto(requestDto, giftCertificate);
        giftCertificate.setLastUpdateDate(Instant.now());

        saveTags(giftCertificate.getTags(), giftCertificate.getId());

        return mapper.toResponseDto(giftCertificateDao.update(giftCertificate));
    }

    @Override
    public void deleteById(UUID id) {
        if (!giftCertificateDao.existsById(id)) {
            throw new NotFoundException(String.format(GIFT_CERTIFICATE_NOT_FOUND_BY_ID, id));
        }
        giftCertificateDao.deleteById(id);
    }

    @Override
    public List<GiftCertificateResponseDto> getAllByCriteria(GiftCertificateCriteria criteria) {
        List<List<GiftCertificate>> lists = new ArrayList<>();

        if (Objects.nonNull(criteria.getTagName())) {
            lists.add(giftCertificateDao.findAllByTagName(criteria.getTagName()));
        }
       // if (Objects.nonNull(criteria.getD))


        return null;
    }
}
