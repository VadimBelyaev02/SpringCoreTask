package com.vadim.springcore.dao.impl;

import com.vadim.springcore.dao.GiftCertificateDao;
import com.vadim.springcore.entity.GiftCertificate;
import com.vadim.springcore.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    private final JdbcTemplate template;

    private final RowMapper<GiftCertificate> mapper = (rs, rowNum) -> {
        final UUID id = UUID.fromString(rs.getString("id"));
        final String name = rs.getString("name");
        final BigDecimal price = rs.getBigDecimal("price");
        final Instant createDate = rs.getObject("create_date", Instant.class);
        final Instant lastUpdateDate = rs.getObject("last_update_date", Instant.class);
        final String description = rs.getString("description");
        final Integer duration = rs.getInt("duration");

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
        GiftCertificate giftCertificate = template.queryForObject(SQL_FIND_BY_ID, mapper, id);
        return Optional.ofNullable(giftCertificate);
    }

    @Override
    public GiftCertificate save(GiftCertificate giftCertificate) {
        final String SQL_SAVE = "INSERT INTO gift_certificates (name, price, duration, create_date, last_update_date, description) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", giftCertificate.getName());
        parameters.addValue("price", giftCertificate.getPrice());
        parameters.addValue("duration", giftCertificate.getDuration());
        parameters.addValue("create_date", giftCertificate.getCreateDate());
        parameters.addValue("last_update_date", giftCertificate.getLastUpdateDate());
        parameters.addValue("description", giftCertificate.getDescription());

        int rowAffected = template.update(SQL_SAVE, parameters, keyHolder);
        // maybe it's good to check whether exactly one tow was updated but I don't know how to do it properly now
        if (Objects.isNull(keyHolder.getKey()) || rowAffected != 1) {
            throw new RuntimeException();
        }
        giftCertificate.setId(UUID.fromString(keyHolder.getKey().toString()));
        return giftCertificate;
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        final String SQL_UPDATE = "UPDATE tags SET name = ?, price = ?, duration = ?, create_date = ?, last_update_date = ?, description = ? WHERE id = ?";

        int rowAffected = template.update(SQL_UPDATE, giftCertificate.getName(), giftCertificate.getPrice(), giftCertificate.getDuration(),
                giftCertificate.getCreateDate(), giftCertificate.getLastUpdateDate(), giftCertificate.getDescription(), giftCertificate.getId());

        return giftCertificate;
    }

    @Override
    public void deleteById(UUID id) {
        final String SQL_DELETE_BY_ID = "DELETE FROM tags WHERE id = ?";

        int rowAffected = template.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public boolean existsById(UUID id) {
        return false;
    }
}
