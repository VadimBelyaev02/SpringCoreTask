package com.vadim.springcore.dao.impl;

import com.vadim.springcore.dao.TagDao;
import com.vadim.springcore.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

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
        final String SQL_SAVE = "INSERT INTO tags (name) VALUES (?) RETURNING id";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowAffected = template.update(SQL_SAVE, tag.getName(), keyHolder);
        // maybe it's good to check whether exactly one tow was updated but I don't know how to do it properly now

        if (Objects.isNull(keyHolder.getKey()) || rowAffected != 1) {
            throw new RuntimeException();
        }
        tag.setId(UUID.fromString(keyHolder.getKey().toString()));
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
}
