package com.vadim.springcore.service;

import com.vadim.springcore.criteria.GiftCertificateCriteria;
import com.vadim.springcore.dto.request.GiftCertificateRequestDto;
import com.vadim.springcore.dto.response.GiftCertificateResponseDto;

import java.util.List;
import java.util.UUID;

public interface GiftCertificateService {

    GiftCertificateResponseDto getById(UUID id);

    List<GiftCertificateResponseDto> getAll();
    GiftCertificateResponseDto save(GiftCertificateRequestDto requestDto);

    GiftCertificateResponseDto update(UUID id, GiftCertificateRequestDto requestDto);

    void deleteById(UUID id);

    List<GiftCertificateResponseDto> getAllByCriteria(GiftCertificateCriteria criteria);
}
