package com.vadim.springcore.dao;

import com.vadim.springcore.model.entity.GiftCertificateTag;
import com.vadim.springcore.model.entity.GiftCertificateTagId;

import java.util.List;

public interface GiftCertificateTagDao extends CrudDao<GiftCertificateTag, GiftCertificateTagId> {

    void saveBatch(List<GiftCertificateTag> giftCertificateTags);
}
