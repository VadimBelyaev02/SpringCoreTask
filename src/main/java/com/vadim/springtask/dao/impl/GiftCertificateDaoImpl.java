package com.vadim.springtask.dao.impl;

import com.vadim.springtask.dao.GiftCertificateDao;
import com.vadim.springtask.model.entity.GiftCertificate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    @PersistenceContext
    private final EntityManager manager;

    @Override
    public List<GiftCertificate> findAll() {
        final String query = "SELECT s FROM GiftCertificate s";
        return manager.createQuery(query, GiftCertificate.class).getResultList();
    }

    @Override
    public Optional<GiftCertificate> findById(UUID id) {
        GiftCertificate giftCertificate = manager.find(GiftCertificate.class, id);
        if (Objects.nonNull(giftCertificate)) {
            manager.detach(giftCertificate);
        }
        return Optional.ofNullable(giftCertificate);

    }

    @Override
    public GiftCertificate save(GiftCertificate giftCertificate) {
        manager.persist(giftCertificate);
        return giftCertificate;
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        return manager.merge(giftCertificate);
    }

    @Override
    public void deleteById(UUID id) {
        final String query = "DELETE FROM GiftCertificate gc WHERE gc.id = :id";
        manager.createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<GiftCertificate> findAll(Integer page, Integer pageSize) {
        String query = "SELECT gc FROM GiftCertificate gc";
        TypedQuery<GiftCertificate> typedQuery = manager.createQuery(query, GiftCertificate.class);
        typedQuery.setFirstResult(page * pageSize);
        typedQuery.setMaxResults(pageSize);
        return typedQuery.getResultList();
    }

    @Override
    public boolean existsById(UUID id) {
        return Objects.nonNull(manager.find(GiftCertificate.class, id));
    }

    @Override
    public List<GiftCertificate> findAllByTagName(String tagName) {
        final String query = "SELECT gc FROM GiftCertificate gc\n" +
                "WHERE gc.id IN (\n" +
                "    SELECT gct FROM GiftCertificateTag gct\n" +
                "    JOIN Tag t ON t.id = gct.id.tagId\n" +
                "    WHERE t.name = :name\n" +
                "    )";
        return manager.createQuery(query, GiftCertificate.class)
                .setParameter("name", tagName)
                .getResultList();
    }

    @Override
    public List<GiftCertificate> findAllLikeName(String partOfName) {
        final String query = "SELECT gc FROM GiftCertificate gc WHERE gc.name ILIKE :name";
        return manager.createQuery(query, GiftCertificate.class)
                .setParameter("name", partOfName)
                .getResultList();

    }

    @Override
    public List<GiftCertificate> findAllLikeDescription(String partOfDescription) {
        final String query = "SELECT gc FROM GiftCertificate gc WHERE LOWER(gc.description) LIKE LOWER(:description)";
        return manager.createQuery(query, GiftCertificate.class)
                .setParameter("name", partOfDescription)
                .getResultList();
    }
}
