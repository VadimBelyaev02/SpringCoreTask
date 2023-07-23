package com.vadim.springcore.service.impl;

import com.vadim.springcore.dao.GiftCertificateDao;
import com.vadim.springcore.dao.GiftCertificateTagDao;
import com.vadim.springcore.dao.TagDao;
import com.vadim.springcore.exception.NotFoundException;
import com.vadim.springcore.model.criteria.ComparatorUtil;
import com.vadim.springcore.model.criteria.GiftCertificateCriteria;
import com.vadim.springcore.model.dto.mapper.GiftCertificateMapper;
import com.vadim.springcore.model.dto.request.GiftCertificateRequestDto;
import com.vadim.springcore.model.dto.response.GiftCertificateResponseDto;
import com.vadim.springcore.model.entity.GiftCertificate;
import com.vadim.springcore.model.entity.GiftCertificateTag;
import com.vadim.springcore.model.entity.GiftCertificateTagId;
import com.vadim.springcore.model.entity.Tag;
import com.vadim.springcore.service.GiftCertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static com.vadim.springcore.util.constants.GiftCertificateConstants.GIFT_CERTIFICATE_NOT_FOUND_BY_ID;

@Service
@RequiredArgsConstructor
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDao giftCertificateDao;
    private final GiftCertificateTagDao giftCertificateTagDao;
    private final GiftCertificateMapper mapper;
    private final TagDao tagDao;

    @Override
    @Transactional(readOnly = true)
    public GiftCertificateResponseDto getById(UUID id) {
        GiftCertificate giftCertificate = giftCertificateDao.findById(id).orElseThrow(() ->
                new NotFoundException(String.format(GIFT_CERTIFICATE_NOT_FOUND_BY_ID, id))
        );
        return mapper.toResponseDto(giftCertificate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GiftCertificateResponseDto> getAll() {
        return giftCertificateDao.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public GiftCertificateResponseDto save(GiftCertificateRequestDto requestDto) {
        GiftCertificate giftCertificate = mapper.toEntity(requestDto);
        giftCertificate.setCreateDate(Instant.now());
        giftCertificate.setLastUpdateDate(giftCertificate.getCreateDate());

        final GiftCertificate savedGiftCertificate = giftCertificateDao.save(giftCertificate);
        savedGiftCertificate.setTags(giftCertificate.getTags());

        saveTags(giftCertificate.getTags(), savedGiftCertificate.getId());
        return mapper.toResponseDto(savedGiftCertificate);
    }

    private void saveTags(List<Tag> tags, UUID giftCertificateId) {
        if (Objects.nonNull(tags)) {
            tags.forEach(
                    tag -> {
                        tag = tagDao.save(tag);
                        giftCertificateTagDao.save(new GiftCertificateTag(new GiftCertificateTagId(giftCertificateId, tag.getId())));
                    }
            );
        }
    }

    @Override
    @Transactional
    public GiftCertificateResponseDto update(UUID id, GiftCertificateRequestDto requestDto) {
        GiftCertificate giftCertificate = giftCertificateDao.findById(id).orElseThrow(() ->
                new NotFoundException(String.format(GIFT_CERTIFICATE_NOT_FOUND_BY_ID, id))
        );
        mapper.updateGiftCertificateFromDto(requestDto, giftCertificate);
        giftCertificate.setLastUpdateDate(Instant.now());

        saveTags(giftCertificate.getTags(), giftCertificate.getId());
        GiftCertificate updatedGiftCertificate = giftCertificateDao.update(giftCertificate);
        updatedGiftCertificate.setTags(tagDao.findAllByGiftCertificateId(updatedGiftCertificate.getId()));

        return mapper.toResponseDto(updatedGiftCertificate);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        if (!giftCertificateDao.existsById(id)) {
            throw new NotFoundException(String.format(GIFT_CERTIFICATE_NOT_FOUND_BY_ID, id));
        }
        giftCertificateTagDao.deleteByGiftCertificateId(id);
        giftCertificateDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GiftCertificateResponseDto> getAllByCriteria(GiftCertificateCriteria criteria) {
        List<List<GiftCertificate>> lists = new ArrayList<>(3);
        List<GiftCertificate> list = new ArrayList<>();

//        Optional.ofNullable(criteria.getTagName()).ifPresent(name -> lists.add(giftCertificateDao.findAllByTagName(name)));
//        Optional.ofNullable(criteria.getPartOfName()).ifPresent(name -> lists.add(giftCertificateDao.findAllLikeName(name)));
//        Optional.ofNullable(criteria.getPartOfDescription()).ifPresent(descr -> lists.add(giftCertificateDao.findAllLikeDescription(descr)));

        Optional.ofNullable(criteria.getTagName()).ifPresent(name -> list.addAll(giftCertificateDao.findAllByTagName(name)));
        Optional.ofNullable(criteria.getPartOfName()).ifPresent(name -> list.addAll(giftCertificateDao.findAllLikeName(name)));
        Optional.ofNullable(criteria.getPartOfDescription()).ifPresent(descr -> list.addAll(giftCertificateDao.findAllLikeDescription(descr)));



//        if (Objects.nonNull(criteria.getTagName())) {
//            lists.add(giftCertificateDao.findAllByTagName(criteria.getTagName()));
//        }
//        if (Objects.nonNull(criteria.getPartOfName())) {
//            lists.add(giftCertificateDao.findAllLikeName(criteria.getPartOfName()));
//        }
//        if (Objects.nonNull(criteria.getPartOfDescription())) {
//            lists.add(giftCertificateDao.findAllLikeDescription(criteria.getPartOfDescription()));
//        }
        // it's not conjunction, needs to be rewritten
        return lists.stream()
                .flatMap(List::stream).toList()
                .stream()
                .sorted(ComparatorUtil.getComparator(criteria.getFirstSortBy(), criteria.getFirstSortType())
                        .thenComparing(ComparatorUtil.getComparator(criteria.getSecondSortBy(), criteria.getSecondSortType())))
                .peek(gc -> gc.setTags(tagDao.findAllByGiftCertificateId(gc.getId())))
                .map(mapper::toResponseDto)
                .toList();
    }
}
