package com.vadim.springcore.service;

import com.vadim.springcore.dto.request.GiftCertificateRequestDto;
import com.vadim.springcore.dto.response.GiftCertificateResponseDto;

import java.util.List;
import java.util.UUID;

public interface GiftCertificateService {

    GiftCertificateResponseDto getById(UUID id);

    List<GiftCertificateResponseDto> getAll();
    GiftCertificateResponseDto save(GiftCertificateRequestDto requestDto);

    GiftCertificateResponseDto update(GiftCertificateRequestDto requestDto);

    void deleteById(UUID id);
}
