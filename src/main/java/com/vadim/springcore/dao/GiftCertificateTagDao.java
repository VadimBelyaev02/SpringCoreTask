package com.vadim.springcore.dao;

import com.vadim.springcore.model.entity.GiftCertificateTag;
import com.vadim.springcore.model.entity.GiftCertificateTagId;

import java.util.List;
import java.util.UUID;

public interface GiftCertificateTagDao extends CrudDao<GiftCertificateTag, GiftCertificateTagId> {

    void saveBatch(List<GiftCertificateTag> giftCertificateTags);

    void deleteByGiftCertificateId(UUID id);
}
