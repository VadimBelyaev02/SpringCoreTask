package com.vadim.springcore.dao;

import com.vadim.springcore.model.entity.GiftCertificateTag;
import com.vadim.springcore.model.entity.GiftCertificateTagId;

import java.util.UUID;

public interface GiftCertificateTagDao extends CrudDao<GiftCertificateTag, GiftCertificateTagId> {

    void deleteByGiftCertificateId(UUID id);

    void deleteByTagId(UUID id);
}
