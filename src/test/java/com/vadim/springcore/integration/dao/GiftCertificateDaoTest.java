package com.vadim.springcore.integration.dao;

import com.vadim.springcore.config.TestConfig;
import com.vadim.springcore.dao.GiftCertificateDao;
import com.vadim.springcore.dao.TagDao;
import com.vadim.springcore.factory.entity.GiftCertificateFactory;
import com.vadim.springcore.model.entity.GiftCertificate;
import com.vadim.springcore.model.entity.GiftCertificateTag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.vadim.springcore.util.constants.GiftCertificateTestConstants.ID;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
@Transactional
public class GiftCertificateDaoTest {

    @Autowired
    private GiftCertificateDao dao;
    private final int numberOfGiftCertificates = 3;

    @Test
    void existsByIdTestWithExistingId() {
        GiftCertificate giftCertificate = dao.findAll().get(0);

        assertTrue(dao.existsById(giftCertificate.getId()));
    }

    @Test
    void existsByIdTestWithNotExistingId() {
        UUID id = UUID.randomUUID();

        assertFalse(dao.existsById(id));
    }

    @Test
    void findAllByTagNameTestWithExistingTagName() {
        List<GiftCertificate> giftCertificates = dao.findAllByTagName("qwertynamE2222");

        assertEquals(2, giftCertificates.size());
    }

    @Test
    void findAllLikeTagNameTest() {
        List<GiftCertificate> giftCertificates = dao.findAllLikeName("nAm");
giftCertificates.forEach(System.out::println);
        assertEquals(2, giftCertificates.size());
    }

    @Test
    void findAllLikeDescription() {
        List<GiftCertificate> giftCertificates = dao.findAllLikeDescription("DeScR");

        assertEquals(2, giftCertificates.size());
    }

    @Test
    void findAllTest() {
        assertEquals(numberOfGiftCertificates, dao.findAll().size());
    }

    @Test
    void findByIdTestWithExistingId() {
        GiftCertificate giftCertificate = dao.findAll().get(0);
        UUID id = giftCertificate.getId();

        Optional<GiftCertificate> optionalGiftCertificate = dao.findById(id);

        assertTrue(optionalGiftCertificate.isPresent());
        assertEquals(giftCertificate, optionalGiftCertificate.get());
    }

    @Test
    void findByIdTestWithNotExistingId() {
        UUID id = UUID.randomUUID();

        Optional<GiftCertificate> optionalGiftCertificate = dao.findById(id);

        assertFalse(optionalGiftCertificate.isPresent());
    }

    @Test
    void saveTest() {
        GiftCertificate giftCertificate = GiftCertificateFactory.getTagFactory().getInstance();

        GiftCertificate savedGiftCertificate = dao.save(giftCertificate);
        giftCertificate.setId(savedGiftCertificate.getId());

        assertEquals(numberOfGiftCertificates + 1, dao.findAll().size());
        assertEquals(giftCertificate, savedGiftCertificate);
    }

    @Test
    void updateTest() {
        GiftCertificate giftCertificate = dao.findAll().get(0);
        giftCertificate.setDuration(giftCertificate.getDuration() + 1);

        dao.update(giftCertificate);

        Optional<GiftCertificate> optionalGiftCertificate = dao.findById(giftCertificate.getId());

        assertEquals(numberOfGiftCertificates, dao.findAll().size());
        assertTrue(optionalGiftCertificate.isPresent());
        assertEquals(giftCertificate, optionalGiftCertificate.get());
    }

    @Test
    void deleteByIdTest() {
        UUID id = UUID.fromString("21be96dc-cfa8-4a8c-90eb-fd5be1568024");

        dao.deleteById(id);

        Optional<GiftCertificate> optionalGiftCertificate = dao.findById(id);

        assertEquals(numberOfGiftCertificates - 1, dao.findAll().size());
        assertFalse(optionalGiftCertificate.isPresent());
    }
}
