package com.vadim.springcore.dao.impl;

import com.vadim.springcore.dao.TagDao;
import com.vadim.springcore.entity.Tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TagDaoImpl implements TagDao {
    @Override
    public List<Tag> findAll() {
        return null;
    }

    @Override
    public Optional<Tag> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Tag save(Tag tag) {
        return null;
    }

    @Override
    public Tag update(Tag tag) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }
}
