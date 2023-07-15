package com.vadim.springcore.dao;

import com.vadim.springcore.entity.Tag;

import java.util.UUID;

public interface TagDao extends CrudDao<Tag, UUID> {

    boolean existsById(UUID id);
}
