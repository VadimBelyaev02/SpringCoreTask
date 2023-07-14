package com.vadim.springcore.dao.impl;

import com.vadim.springcore.dao.GiftCertificateDao;
import com.vadim.springcore.entity.GiftCertificate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GiftCertificateDaoImpl implements GiftCertificateDao {
    @Override
    public List<GiftCertificate> findAll() {
        return null;
    }

    @Override
    public Optional<GiftCertificate> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public GiftCertificate save(GiftCertificate giftCertificate) {
        return null;
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }
}
