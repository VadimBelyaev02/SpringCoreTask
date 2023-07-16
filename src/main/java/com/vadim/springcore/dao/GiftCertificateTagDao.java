package com.vadim.springcore.dao;

import com.vadim.springcore.dao.CrudDao;
import com.vadim.springcore.entity.GiftCertificate;
import com.vadim.springcore.entity.GiftCertificateTag;
import com.vadim.springcore.entity.GiftCertificateTagId;

import java.util.List;

public interface GiftCertificateTagDao extends CrudDao<GiftCertificateTag, GiftCertificateTagId> {

    void saveBatch(List<GiftCertificateTag> giftCertificateTags);
}
