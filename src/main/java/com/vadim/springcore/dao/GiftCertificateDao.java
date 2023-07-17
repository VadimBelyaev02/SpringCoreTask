package com.vadim.springcore.dao;

import com.vadim.springcore.entity.GiftCertificate;

import java.util.List;
import java.util.UUID;

public interface GiftCertificateDao extends CrudDao<GiftCertificate, UUID> {

    boolean existsById(UUID id);

    List<GiftCertificate> findAllByTagName(String tagName);
}
