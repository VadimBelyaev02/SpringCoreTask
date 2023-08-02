package com.vadim.springtask.dao;

import com.vadim.springtask.model.entity.GiftCertificate;

import java.util.List;
import java.util.UUID;

public interface GiftCertificateDao extends CrudDao<GiftCertificate, UUID> {

    List<GiftCertificate> findAll(Integer page, Integer pageSize);
    boolean existsById(UUID id);

    List<GiftCertificate> findAllByTagName(String tagName);

    List<GiftCertificate> findAllLikeName(String partOfTagName);

    List<GiftCertificate> findAllLikeDescription(String partOfDescription);
}
