package com.vadim.springcore.integration.dao;

import com.vadim.springcore.config.TestConfig;
import com.vadim.springcore.dao.TagDao;
import com.vadim.springcore.factory.entity.TagFactory;
import com.vadim.springcore.model.entity.GiftCertificate;
import com.vadim.springcore.model.entity.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import com.vadim.springcore.dao.GiftCertificateTagDao;
import com.vadim.springcore.dao.TagDao;
import com.vadim.springcore.exception.DuplicateRecordException;
import com.vadim.springcore.exception.NotFoundException;
import com.vadim.springcore.factory.dto.request.TagRequestDtoFactory;
import com.vadim.springcore.factory.dto.response.TagResponseDtoFactory;
import com.vadim.springcore.factory.entity.TagFactory;
import com.vadim.springcore.model.dto.mapper.TagMapper;
import com.vadim.springcore.model.dto.request.TagRequestDto;
import com.vadim.springcore.model.dto.response.TagResponseDto;
import com.vadim.springcore.model.entity.Tag;
import com.vadim.springcore.service.impl.TagServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static com.vadim.springcore.util.constants.TagTestConstants.ID;
import static com.vadim.springcore.util.constants.TagTestConstants.NAME;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
