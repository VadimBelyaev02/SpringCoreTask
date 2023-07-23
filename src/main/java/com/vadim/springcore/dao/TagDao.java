package com.vadim.springcore.dao;

import com.vadim.springcore.model.entity.Tag;

import java.util.List;
import java.util.UUID;

public interface TagDao extends CrudDao<Tag, UUID> {

    boolean existsById(UUID id);

  //  Tag saveIfNotExists(Tag tag);

    List<Tag> findAllByGiftCertificateId(UUID id);

    boolean existsByName(String name);
}
