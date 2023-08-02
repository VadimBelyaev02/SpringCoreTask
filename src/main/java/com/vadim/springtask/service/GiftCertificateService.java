package com.vadim.springtask.service;

import com.vadim.springtask.model.criteria.GiftCertificateCriteria;
import com.vadim.springtask.model.dto.request.GiftCertificateRequestDto;
import com.vadim.springtask.model.dto.response.GiftCertificateResponseDto;
import com.vadim.springtask.model.dto.response.PageResponseDto;

import java.util.List;
import java.util.UUID;

public interface GiftCertificateService {

    GiftCertificateResponseDto getById(UUID id);

    PageResponseDto<GiftCertificateResponseDto> getAll(Integer page, Integer pageSize);
    GiftCertificateResponseDto save(GiftCertificateRequestDto requestDto);

    GiftCertificateResponseDto update(UUID id, GiftCertificateRequestDto requestDto);

    void deleteById(UUID id);

    List<GiftCertificateResponseDto> getAllByCriteria(GiftCertificateCriteria criteria);
}
