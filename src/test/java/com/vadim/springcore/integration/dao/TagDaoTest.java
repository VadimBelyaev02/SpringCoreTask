package com.vadim.springcore.integration.dao;

import com.vadim.springcore.config.TestConfig;
import com.vadim.springcore.dao.TagDao;
import com.vadim.springcore.factory.entity.TagFactory;
import com.vadim.springcore.model.entity.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@Transactional
// active profile
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration

//@Rollback
public class TagDaoTest {

    @Autowired
    private TagDao dao;

    /*
        boolean existsById(UUID id);

        Tag saveIfNotExists(Tag tag);

        List<Tag> findAllByGiftCertificateId(UUID id);

        boolean existsByName(String name);

        List<T> findAll();

        Optional<T> findById(K id);

       1 T save(T t);

        T update(T t);

        void deleteById(K id);

     */

    @Test
    void save() {
        Tag tag = TagFactory.getTagFactory().getInstance();
        tag.setId(null);
        Tag savedTag = dao.save(tag);

        tag.setId(savedTag.getId());

        assertEquals(tag, savedTag);
    }
    @Test
    void existsById() {

    }
}
