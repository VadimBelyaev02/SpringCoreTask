package com.vadim.springcore.dao.impl;

import com.vadim.springcore.dao.GiftCertificateDao;
import com.vadim.springcore.model.entity.GiftCertificate;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.time.Instant;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    private final JdbcTemplate template;

    private final RowMapper<GiftCertificate> mapper = (rs, rowNum) -> {
        final UUID id = UUID.fromString(rs.getString("id"));
        final String name = rs.getString("name");
        final BigDecimal price = rs.getBigDecimal("price");
        final Timestamp createDateTimestamp = rs.getTimestamp("create_date");
        final Timestamp lastUpdateDateTimestamp = rs.getTimestamp("last_update_date");
        final String description = rs.getString("description");
        final Integer duration = rs.getInt("duration");

        Instant createDate = createDateTimestamp.toInstant();
        Instant lastUpdateDate = lastUpdateDateTimestamp.toInstant();

        return GiftCertificate.builder()
                .price(price)
                .description(description)
                .duration(duration)
                .createDate(createDate)
                .lastUpdateDate(lastUpdateDate)
                .name(name)
                .id(id)
                .build();
    };

    @Override
    public List<GiftCertificate> findAll() {
        final String SQL = "SELECT * FROM gift_certificates";
        return template.query(SQL, mapper);
    }

    @Override
    public Optional<GiftCertificate> findById(UUID id) {
        final String SQL_FIND_BY_ID = "SELECT * FROM gift_certificates WHERE id = ?";
        return template.query(SQL_FIND_BY_ID, mapper, id).stream().findFirst();
    }

    @Override
    public GiftCertificate save(GiftCertificate gc) {
        final String SQL_INSERT = "INSERT INTO gift_certificates (id, name, price, duration, create_date, last_update_date, description) VALUES (uuid_generate_v4(), ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowAffected = template.update(con -> {
            PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, gc.getName());
            ps.setBigDecimal(2, gc.getPrice());
            ps.setInt(3, gc.getDuration());
            ps.setObject(4, Timestamp.from(gc.getCreateDate()));
            ps.setObject(5, Timestamp.from(gc.getLastUpdateDate()));
            ps.setObject(6, gc.getDescription());
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        Object object = Objects.requireNonNull(keys).get("id");
        if (Objects.isNull(object) || rowAffected != 1) {
            throw new RuntimeException();
        }
        gc.setId(UUID.fromString(object.toString()));

//        final String SQL_INSERT_GIFT_CERTIFICATES_TAGS = "INSERT INTO gift_certificates_tags (gift_certificate_id, tag_id) VALUES(?, ?)";
//        gc.getTags().forEach(
//                final String SQL_FIND_TAG_BY_NAME =
//                tag -> template.update(SQL_INSERT_GIFT_CERTIFICATES_TAGS, gc.getId(), tag.getId())
//        );
        return gc;
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        final String SQL_UPDATE = "UPDATE tags SET name = ?, price = ?, duration = ?, create_date = ?, last_update_date = ?, description = ? WHERE id = ?";

        int rowAffected = template.update(SQL_UPDATE, giftCertificate.getName(), giftCertificate.getPrice(), giftCertificate.getDuration(),
                Timestamp.from(giftCertificate.getCreateDate()), Timestamp.from(giftCertificate.getLastUpdateDate()), giftCertificate.getDescription(), giftCertificate.getId());

        return giftCertificate;
    }

    @Override
    public void deleteById(UUID id) {
        final String SQL_DELETE_BY_ID = "DELETE FROM tags WHERE id = ?";

        int rowAffected = template.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public boolean existsById(UUID id) {
        final String SQL_FIND_BY_ID = "SELECT * FROM gift_certificates WHERE id = ?";
        return template.query(SQL_FIND_BY_ID, mapper, id).size() > 0;
    }

    @Override
    public List<GiftCertificate> findAllByTagName(String tagName) {
        final String SQL = "SELECT gift_certificates.* FROM gift_certificates\n" +
                "WHERE gift_certificates.id IN (\n" +
                "    SELECT gift_certificate_id FROM gift_certificates_tags\n" +
                "    JOIN tags ON tags.id = gift_certificates_tags.tag_id\n" +
                "    WHERE tags.name = ?\n" +
                "    )";
        return template.query(SQL, mapper, tagName);
    }

    @Override
    public List<GiftCertificate> findAllLikeTagName(String partOfTagName) {
        final String SQL = "SELECT gift_certificates.* FROM gift_certificates\n" +
                "WHERE gift_certificates.id IN (\n" +
                "    SELECT gift_certificate_id FROM gift_certificates_tags\n" +
                "    JOIN tags ON tags.id = gift_certificates_tags.tag_id\n" +
                "    WHERE tags.name ILIKE ?);";
        return template.query(SQL, mapper, '%' + partOfTagName + '%');
    }

    @Override
    public List<GiftCertificate> findAllLikeDescription(String partOfDescription) {
        final String SQL = "SELECT * FROM gift_certificates WHERE description ILIKE ?";
        return template.query(SQL, mapper, '%' + partOfDescription + '%');
    }
}
