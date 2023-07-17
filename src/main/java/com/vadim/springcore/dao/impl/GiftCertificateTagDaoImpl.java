package com.vadim.springcore.dao.impl;

import com.vadim.springcore.dao.GiftCertificateTagDao;
import com.vadim.springcore.model.entity.GiftCertificate;
import com.vadim.springcore.model.entity.GiftCertificateTag;
import com.vadim.springcore.model.entity.GiftCertificateTagId;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class GiftCertificateTagDaoImpl implements GiftCertificateTagDao {

    private final JdbcTemplate template;

    private final RowMapper<GiftCertificateTag> mapper = (rs, rowNum) -> {
        final UUID giftCertificate_Id = UUID.fromString(rs.getString("gift_certificate_id"));
        final UUID tagId = UUID.fromString(rs.getString("tag_id"));

        return GiftCertificateTag.builder()
                .id(new GiftCertificateTagId(giftCertificate_Id, tagId))
                .build();
    };

    @Override
    public List<GiftCertificateTag> findAll() {
        final String SQL = "SELECT * FROM gift_certificates_tags";
        return template.query(SQL, mapper);
    }

    @Override
    public Optional<GiftCertificateTag> findById(GiftCertificateTagId id) {
        final String SQL_FIND_BY_ID = "SELECT * FROM gift_certificates_tags WHERE gift_certificate_id = ? AND tag_id = ?";
        return template.query(SQL_FIND_BY_ID, mapper, id.getGiftCertificateId(), id.getTagId()).stream()
                .findFirst();
    }

    @Override
    public GiftCertificateTag save(GiftCertificateTag giftCertificateTag) {
        final String SQL_INSERT = "INSERT INTO gift_certificates_tags (gift_certificate_id, tag_id) VALUES (?, ?)";
        int rowAffected = template.update(SQL_INSERT, giftCertificateTag.getId().getGiftCertificateId(), giftCertificateTag.getId().getTagId());

        return giftCertificateTag;
    }

    @Override
    public GiftCertificateTag update(GiftCertificateTag giftCertificateTag) {
        return null;
    }

    @Override
    public void deleteById(GiftCertificateTagId id) {
        final String SQL_DELETE_BY_ID = "DELETE FROM gift_certificates_tags WHERE gift_certificate_id = ? AND tag_id = ?";
        int rowAffected = template.update(SQL_DELETE_BY_ID, id.getGiftCertificateId(), id.getGiftCertificateId());
    }

    @Override
    public void saveBatch(List<GiftCertificateTag> giftCertificateTags) {
        giftCertificateTags.forEach(
                this::save
        );
    }
}
