package com.vadim.springcore.dao;

import com.vadim.springcore.entity.GiftCertificate;

import java.util.UUID;

public interface GiftCertificateDao extends CrudDao<GiftCertificate, UUID> {

    boolean existsById(UUID id);
}
