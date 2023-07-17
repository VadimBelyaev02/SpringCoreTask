package com.vadim.springcore.dao.impl;

import com.vadim.springcore.dao.TagDao;
import com.vadim.springcore.entity.Tag;
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
        final String SQL_FIND_BY_ID = "SELECT * FROM tags WHERE id = ?";
        Tag tag = template.queryForObject(SQL_FIND_BY_ID, mapper, id);
        return Optional.ofNullable(tag);
    }

    @Override
    public Tag save(Tag tag) {
        final String SQL_INSERT = "INSERT INTO tags (name) VALUES (?) RETURNING id";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowAffected = template.update(con -> {
            PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tag.getName());
            ps.setString(2, tag.getName());
            return ps;
        }, keyHolder);
        template.update(SQL_INSERT, tag.getName(), tag.getName());
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
        final String SQL_UPDATE = "UPDATE tags SET name = ? WHERE id = ?";

        int rowAffected = template.update(SQL_UPDATE, tag.getName(), tag.getId());

        return tag;
    }

    @Override
    public void deleteById(UUID id) {
        final String SQL_DELETE_BY_ID = "DELETE FROM tags WHERE id = ?";

        int rowAffected = template.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public boolean existsById(UUID id) {
        final String SQL_FIND_BY_ID = "SELECT * FROM tags WHERE id = ?";
        Tag tag = template.queryForObject(SQL_FIND_BY_ID, mapper, id);
        return tag != null;
    }


    @Override
    public Tag saveIfNotExists(Tag tag) {
        final String SQL_SELECT = "SELECT * FROM tags WHERE name = ?";
        Tag tagFromDb = template.queryForObject(SQL_SELECT, mapper, tag.getName());
        if (Objects.nonNull(tagFromDb)) {
            return tagFromDb;
        }
        return save(tag);
    }
}
