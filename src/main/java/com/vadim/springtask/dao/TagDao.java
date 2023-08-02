package com.vadim.springtask.dao;

import com.vadim.springtask.model.entity.Tag;

import java.util.List;
import java.util.UUID;

public interface TagDao extends CrudDao<Tag, UUID> {

    List<Tag> findAll(Integer page, Integer pageSize);
    boolean existsById(UUID id);

    Tag saveIfNotExistsByName(Tag tag);

    List<Tag> findAllByGiftCertificateId(UUID id);

    boolean existsByName(String name);
}
