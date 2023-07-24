package com.vadim.springtask.service;

import com.vadim.springtask.model.criteria.GiftCertificateCriteria;
import com.vadim.springtask.model.dto.request.GiftCertificateRequestDto;
import com.vadim.springtask.model.dto.response.GiftCertificateResponseDto;

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
