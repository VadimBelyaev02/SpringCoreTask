package com.vadim.springcore.dao.impl;

import com.vadim.springcore.dao.GiftCertificateTagDao;
import com.vadim.springcore.entity.GiftCertificateTag;
import com.vadim.springcore.entity.GiftCertificateTagId;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GiftCertificateTagDaoImpl implements GiftCertificateTagDao {

    private final JdbcTemplate template;

    @Override
    public List<GiftCertificateTag> findAll() {
        return null;
    }

    @Override
    public Optional<GiftCertificateTag> findById(GiftCertificateTagId id) {
        return Optional.empty();
    }

    @Override
    public GiftCertificateTag save(GiftCertificateTag giftCertificateTag) {
        final String SQL_INSERT_GIFT_CERTIFICATE_TAG = "INSERT INTO gift_certificates_tags (gift_certificate_id, tag_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
//        int rowAffected = template.update(SQL_INSERT, giftCertificateTag.getId().getGiftCertificateId(), giftCertificateTag.getId().getTagId());

        return giftCertificateTag;
    }

    @Override
    public GiftCertificateTag update(GiftCertificateTag giftCertificateTag) {
        return null;
    }

    @Override
    public void deleteById(GiftCertificateTagId id) {

    }

    @Override
    public void saveBatch(List<GiftCertificateTag> giftCertificateTags) {
        giftCertificateTags.forEach(
                this::save
        );
    }
}
