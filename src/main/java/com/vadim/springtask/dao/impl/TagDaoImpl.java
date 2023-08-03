package com.vadim.springtask.dao.impl;

import com.vadim.springtask.dao.TagDao;
import com.vadim.springtask.model.entity.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TagDaoImpl implements TagDao {

    @PersistenceContext
    private final EntityManager manager;

    @Override
    public List<Tag> findAll() {
        String query = "SELECT t FROM Tag t";
        return manager.createQuery(query, Tag.class).getResultList();
    }

    @Override
    public Optional<Tag> findById(UUID id) {
        Tag tag = manager.find(Tag.class, id);
        if (Objects.nonNull(tag)) {
       //     manager.detach(tag);
        }
        return Optional.ofNullable(tag);
    }

    @Override
    public Tag save(Tag tag) {
        manager.persist(tag);
        return tag;
    }

    @Override
    public Tag update(Tag tag) {
        return manager.merge(tag);
    }

    @Override
    public void deleteById(UUID id) {
        String query = "DELETE FROM Tag t WHERE t.id = :id";
        manager.createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<Tag> findAll(Integer page, Integer pageSize) {
        String query = "SELECT t FROM Tag t";
        TypedQuery<Tag> typedQuery = manager.createQuery(query, Tag.class);
        typedQuery.setFirstResult(page * pageSize);
        typedQuery.setMaxResults(pageSize);
        return typedQuery.getResultList();
    }

    @Override
    public boolean existsById(UUID id) {
        return Objects.nonNull(manager.find(Tag.class, id));
    }


    @Override
    public Tag saveIfNotExistsByName(Tag tag) {
        final String query = "SELECT t FROM Tag t WHERE t.name = :name";
        Optional<Tag> optionalTag = manager.createQuery(query, Tag.class)
                .setParameter("name", tag.getName())
                .getResultList()
                .stream().findFirst();
        return optionalTag.orElseGet(() -> save(tag));
    }

    @Override
    public List<Tag> findAllByGiftCertificateId(UUID id) {
        final String query = "SELECT t FROM Tag t\n" +
                "JOIN GiftCertificateTag gct on gct.id.tagId = t.id\n" +
                "JOIN GiftCertificate gc on gct.id.giftCertificateId = gc.id\n" +
                "WHERE gc.id = :gcId";
        return manager.createQuery(query, Tag.class)
                .setParameter("gcId", id)
                .getResultList();
    }
        /*
SELECT * FROM tags
JOIN gift_certificates_tags gct on tags.id = gct.tag_id
WHERE tags.id IN (
SELECT tag_id FROM gift_certificates_tags
JOIN gift_certificates gc on gc.id = gift_certificates_tags.gift_certificate_id
WHERE gct.gift_certificate_id = '5bd84a44-194c-4c35-8104-431505d8cef1'
);
     */

    @Override
    public boolean existsByName(String name) {
        final String query = "SELECT t FROM Tag t WHERE t.name = :name";
        return manager.createQuery(query, Tag.class)
                .setParameter("name", name)
                .getResultList().size() > 0;
    }
}
