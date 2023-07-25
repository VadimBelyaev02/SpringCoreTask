package com.vadim.springtask.dao.impl;

import com.vadim.springtask.dao.GiftCertificateDao;
import com.vadim.springtask.dao.GiftCertificateTagDao;
import com.vadim.springtask.model.entity.GiftCertificate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Component
@RequiredArgsConstructor
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    @PersistenceContext
    private final EntityManager manager;

    @Override
    public List<GiftCertificate> findAll() {
        String query = "SELECT s FROM GiftCertificate s";
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
        String query = "DELETE FROM GiftCertificate gc WHERE gc.id = :id";
        manager.createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
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

      //  final String query = "SELECT gc FROM GiftCertificate gc WHERE LOWER(gc.name) LIKE LOWER(:name)";
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
