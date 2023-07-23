package com.vadim.springcore.dao.impl;

import com.vadim.springcore.dao.TagDao;
import com.vadim.springcore.model.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class TagDaoImpl implements TagDao {

    private final JdbcTemplate template;

    private final RowMapper<Tag> mapper = (rs, rowNum) -> {
        final UUID id = UUID.fromString(rs.getString("id"));
        final String name = rs.getString("name");

        return Tag.builder()
                .name(name)
                .id(id)
                .build();
    };

    @Override
    public List<Tag> findAll() {
        final String SQL = "SELECT * FROM tags";
        return template.query(SQL, mapper);
    }

    @Override
    public Optional<Tag> findById(UUID id) {
        final String SQL = "SELECT * FROM tags WHERE id = ?";
        return template.query(SQL, mapper, id).stream().findFirst();
    }

    @Override
    public Tag save(Tag tag) {
        if (Objects.nonNull(tag.getId()) && existsById(tag.getId())) {
            update(tag);
        }

        final String SQL = "INSERT INTO tags (id, name) VALUES (uuid_generate_v4(), ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowAffected = template.update(con -> {
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tag.getName());
            return ps;
        }, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        Object object = Objects.requireNonNull(keys).get("id");
        if (Objects.isNull(object) || rowAffected != 1) {
            throw new RuntimeException();
        }
        tag.setId(UUID.fromString(object.toString()));
        return tag;
    }

    @Override
    public Tag update(Tag tag) {
        final String SQL = "UPDATE tags SET name = ? WHERE id = ?";

        int rowAffected = template.update(SQL, tag.getName(), tag.getId());
        return tag;
    }

    @Override
    public void deleteById(UUID id) {
        final String SQL = "DELETE FROM tags WHERE id = ?";
        int rowAffected = template.update(SQL, id);
    }

    @Override
    public boolean existsById(UUID id) {
        final String SQL = "SELECT * FROM tags WHERE id = ?";
        return template.query(SQL, mapper, id).size() > 0;
    }


//    @Override
//    public Tag saveIfNotExists(Tag tag) {
//        final String SQL = "SELECT * FROM tags WHERE name = ?";
//        Optional<Tag> optionalTag = template.query(SQL, mapper, tag.getName()).stream().findFirst();
//        return optionalTag.orElseGet(() -> save(tag));
//    }

    @Override
    public List<Tag> findAllByGiftCertificateId(UUID id) {
        final String SQL = "SELECT * FROM tags\n" +
                "JOIN gift_certificates_tags gct on gct.tag_id = tags.id\n" +
                "JOIN gift_certificates gc on gct.gift_certificate_id = gc.id\n" +
                "WHERE gift_certificate_id = ?";
        return template.query(SQL, mapper, id);
    }
        /*
SELECT * FROM tags
JOIN gift_certificates_tags gct on tags.id = gct.tag_id
WHERE tags.id IN (
SELECT tag_id FROM gift_certificates_tags
JOIN gift_certificates gc on gc.id = gift_certificates_tags.gift_certificate_id
WHERE gct.gift_certificate_id = '5bd84a44-194c-4c35-8104-431505d8cef1'
);
     */

    @Override
    public boolean existsByName(String name) {
        final String SQL = "SELECT * FROM tags WHERE name = ?";
        return template.query(SQL, mapper, name).size() > 0;
    }
}
