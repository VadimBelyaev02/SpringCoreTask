package com.vadim.springcore.service.impl;

import com.vadim.springcore.criteria.GiftCertificateCriteria;
import com.vadim.springcore.dto.request.GiftCertificateRequestDto;
import com.vadim.springcore.dto.response.GiftCertificateResponseDto;
import com.vadim.springcore.service.GiftCertificateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    @Override
    public GiftCertificateResponseDto getById(UUID id) {
        return null;
    }

    @Override
    public List<GiftCertificateResponseDto> getAll() {
        return null;
    }

    @Override
    public GiftCertificateResponseDto save(GiftCertificateRequestDto requestDto) {
        return null;
    }

    @Override
    public GiftCertificateResponseDto update(UUID id, GiftCertificateRequestDto requestDto) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public List<GiftCertificateResponseDto> getAllByCriteria(GiftCertificateCriteria criteria) {
        return null;
    }
}
