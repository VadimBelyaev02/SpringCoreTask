package com.vadim.springcore.unit;

import com.vadim.springcore.dao.GiftCertificateTagDao;
import com.vadim.springcore.dao.TagDao;
import com.vadim.springcore.exception.DuplicateRecordException;
import com.vadim.springcore.exception.NotFoundException;
import com.vadim.springcore.model.dto.mapper.TagMapper;
import com.vadim.springcore.model.dto.request.TagRequestDto;
import com.vadim.springcore.model.dto.response.TagResponseDto;
import com.vadim.springcore.model.entity.Tag;
import com.vadim.springcore.service.impl.TagServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TagUnitTest {

    @Mock
    private GiftCertificateTagDao giftCertificateTagDao;

    @Mock
    private TagDao tagDao;

    @Mock
    private TagMapper mapper;

    @InjectMocks
    private TagServiceImpl service;

    private Tag tag;

    private TagResponseDto responseDto;

    private TagRequestDto requestDto;

    @BeforeEach
    void setUp() {
        UUID tagId = UUID.randomUUID();
        tag = Tag.builder()
                .id(tagId)
                .name("name")
                .build();

        requestDto = TagRequestDto.builder()
                .name("name")
                .build();

        responseDto = TagResponseDto.builder()
                .id(tagId)
                .name("name")
                .build();
    }

    @Test
    void Given_TagId_When_TagWithIdExists_Then_TagIsReturned() {
        UUID id = UUID.randomUUID();
        Tag tag = new Tag();
        TagResponseDto responseDto = new TagResponseDto();

        when(tagDao.findById(id)).thenReturn(Optional.of(tag));
        when(mapper.toResponseDto(tag)).thenReturn(responseDto);

        TagResponseDto actualResponseDto = service.getById(id);

        assertEquals(responseDto, actualResponseDto);

        verify(tagDao, only()).findById(any());
        verify(mapper, only()).toResponseDto(any());
    }

    @Test
    void Given_TagId_WhenTagIsNotFound_Then_ThrowsNotFoundException() {
        UUID id = UUID.randomUUID();

        when(tagDao.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getById(id));

        verify(tagDao, only()).findById(any());
        verifyNoInteractions(mapper);
    }

    @Test
    void Given_Nothing_When_AllTagsRequested_Then_AllTagsAreReturned() {
        List<Tag> tags = Stream.of(new Tag(), new Tag()).toList();
        List<TagResponseDto> tagResponseDtos = Stream.of(new TagResponseDto(), new TagResponseDto()).toList();
        TagResponseDto responseDto = new TagResponseDto();

        when(tagDao.findAll()).thenReturn(tags);
        when(mapper.toResponseDto(any())).thenReturn(responseDto);

        assertEquals(tagResponseDtos, service.getAll());

        verify(tagDao, only()).findAll();
        verify(mapper, times(2)).toResponseDto(any());
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void Given_TagRequestDto_When_SavingTag_Then_SavedTagIsReturned() {
        String name = requestDto.getName();

        when(tagDao.existsByName(name)).thenReturn(false);
        when(mapper.toEntity(requestDto)).thenReturn(tag);
        when(mapper.toResponseDto(tag)).thenReturn(responseDto);
        when(tagDao.save(tag)).thenReturn(tag);

        assertEquals(responseDto, service.save(requestDto));

        verify(tagDao, times(1)).save(any());
        verify(tagDao, times(1)).existsByName(any());
        verify(mapper, times(1)).toResponseDto(any());
        verify(mapper, times(1)).toEntity(any());
        verifyNoMoreInteractions(mapper, tagDao);
    }

    @Test
    void Given_TagRequestDto_When_SavingTag_Then_DuplicateRecordExceptionIsThrown() {
        String name = "name";

        when(tagDao.existsByName(name)).thenReturn(true);

        assertThrows(DuplicateRecordException.class, () -> service.save(requestDto));

        verify(tagDao, only()).existsByName(name);
        verifyNoInteractions(mapper);
    }

    @Test
    void Given_TagRequestDto_When_UpdatingTag_Then_UpdatedTagIsReturned() {
        String name = requestDto.getName();
        UUID id = UUID.randomUUID();

        when(tagDao.update(tag)).thenReturn(tag);
        when(tagDao.existsByName(name)).thenReturn(false);
        when(tagDao.findById(id)).thenReturn(Optional.of(tag));
        when(mapper.toResponseDto(tag)).thenReturn(responseDto);
        doNothing().when(mapper).updateTagFromDto(requestDto, tag);


        assertEquals(responseDto, service.update(id, requestDto));

        verify(tagDao, times(1)).update(any());
        verify(tagDao, times(1)).existsByName(any());
        verify(tagDao, times(1)).findById(any());
        verify(mapper, times(1)).updateTagFromDto(requestDto, tag);
        verify(mapper, times(1)).toResponseDto(tag);
        verifyNoMoreInteractions(mapper, tagDao);
    }

    @Test
    void Given_TagRequestDto_When_UpdatingTagWithExistedName_Then_DuplicateRecordExceptionIsThrown() {
        String name = requestDto.getName();
        UUID id = UUID.randomUUID();

        when(tagDao.existsByName(name)).thenReturn(true);

        assertThrows(DuplicateRecordException.class, () -> service.update(id, requestDto));

        verify(tagDao, only()).existsByName(name);
        verifyNoInteractions(mapper);
    }

    void Give_TagRequestDto_When_UpdatingTagThatIsNotFoundById_Then_NotFoundExceptionIsThrown() {

    }
}
