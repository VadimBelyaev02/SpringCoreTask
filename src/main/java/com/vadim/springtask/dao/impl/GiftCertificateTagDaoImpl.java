package com.vadim.springtask.dao.impl;

import com.vadim.springtask.dao.GiftCertificateTagDao;
import com.vadim.springtask.model.entity.GiftCertificateTag;
import com.vadim.springtask.model.entity.GiftCertificateTagId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class GiftCertificateTagDaoImpl implements GiftCertificateTagDao {

    @PersistenceContext
    private final EntityManager manager;
    @Override
    public List<GiftCertificateTag> findAll() {
        String query = "SELECT s FROM GiftCertificateTag s";
        return manager.createQuery(query, GiftCertificateTag.class).getResultList();
    }

    @Override
    public Optional<GiftCertificateTag> findById(GiftCertificateTagId id) {
        GiftCertificateTag giftCertificateTag = manager.find(GiftCertificateTag.class, id);
        if (Objects.nonNull(giftCertificateTag)) {
            manager.detach(giftCertificateTag);
        }
        return Optional.ofNullable(giftCertificateTag);
    }

    @Override
    public GiftCertificateTag save(GiftCertificateTag giftCertificateTag) {
        manager.persist(giftCertificateTag);
        return giftCertificateTag;
    }

    @Override
    public GiftCertificateTag update(GiftCertificateTag giftCertificateTag) {
        return manager.merge(giftCertificateTag);
    }

    @Override
    public void deleteById(GiftCertificateTagId id) {
        String query = "DELETE FROM GiftCertificateTag gc WHERE gc.id = :id";
        manager.createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void deleteByGiftCertificateId(UUID id) {
        final String query = "DELETE FROM GiftCertificateTag gct WHERE gct.id.giftCertificate.id = :gcId";
        manager.createQuery(query)
                .setParameter("gcId", id)
                .executeUpdate();
    }

    @Override
    public void deleteByTagId(UUID id) {
        final String query = "DELETE FROM GiftCertificateTag gct WHERE gct.id = :tagId";
        manager.createQuery(query)
                .setParameter("tagId", id)
                .executeUpdate();
    }

    @Override
    public boolean existsById(GiftCertificateTagId id) {
        return Objects.nonNull(manager.find(GiftCertificateTag.class, id));
    }
}
