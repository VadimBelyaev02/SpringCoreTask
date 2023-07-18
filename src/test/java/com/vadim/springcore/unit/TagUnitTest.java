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

    private UUID id;

    private String name;

    @BeforeEach
    void setUp() {
        UUID id = UUID.randomUUID();
        name = "name";
        tag = Tag.builder()
                .id(id)
                .name(name)
                .build();

        requestDto = TagRequestDto.builder()
                .name(name)
                .build();

        responseDto = TagResponseDto.builder()
                .id(id)
                .name(name)
                .build();
    }

    @Test
    void Given_TagId_When_TagWithIdExists_Then_TagIsReturned() {
        when(tagDao.findById(id)).thenReturn(Optional.of(tag));
        when(mapper.toResponseDto(tag)).thenReturn(responseDto);

        TagResponseDto actualResponseDto = service.getById(id);

        assertEquals(responseDto, actualResponseDto);

        verify(tagDao, only()).findById(any());
        verify(mapper, only()).toResponseDto(any());
    }

    @Test
    void Given_TagId_WhenTagIsNotFound_Then_ThrowsNotFoundException() {
        when(tagDao.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getById(id));

        verify(tagDao, only()).findById(any());
        verifyNoInteractions(mapper);
    }

    @Test
    void Given_Nothing_When_AllTagsRequested_Then_AllTagsAreReturned() {
        List<Tag> tags = Stream.of(tag, tag).toList();
        List<TagResponseDto> tagResponseDtos = Stream.of(responseDto, responseDto).toList();

        when(tagDao.findAll()).thenReturn(tags);
        when(mapper.toResponseDto(any())).thenReturn(responseDto);

        assertEquals(tagResponseDtos, service.getAll());

        verify(tagDao, only()).findAll();
        verify(mapper, times(2)).toResponseDto(any());
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void Given_TagRequestDto_When_SavingTag_Then_SavedTagIsReturned() {
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
        when(tagDao.existsByName(name)).thenReturn(true);

        assertThrows(DuplicateRecordException.class, () -> service.save(requestDto));

        verify(tagDao, only()).existsByName(any());
        verifyNoInteractions(mapper);
    }

    @Test
    void Given_TagRequestDto_When_UpdatingTag_Then_UpdatedTagIsReturned() {
        when(tagDao.update(tag)).thenReturn(tag);
        when(tagDao.existsByName(name)).thenReturn(false);
        when(tagDao.findById(id)).thenReturn(Optional.of(tag));
        when(mapper.toResponseDto(tag)).thenReturn(responseDto);
        doNothing().when(mapper).updateTagFromDto(requestDto, tag);


        assertEquals(responseDto, service.update(id, requestDto));

        verify(tagDao, times(1)).update(any());
        verify(tagDao, times(1)).existsByName(any());
        verify(tagDao, times(1)).findById(any());
        verify(mapper, times(1)).updateTagFromDto(any(), any());
        verify(mapper, times(1)).toResponseDto(any());
        verifyNoMoreInteractions(mapper, tagDao);
    }

    @Test
    void Given_TagRequestDto_When_UpdatingTagWithExistedName_Then_DuplicateRecordExceptionIsThrown() {
        when(tagDao.existsByName(name)).thenReturn(true);

        assertThrows(DuplicateRecordException.class, () -> service.update(id, requestDto));

        verify(tagDao, only()).existsByName(any());
        verifyNoInteractions(mapper);
    }

    @Test
    void Given_TagRequestDto_When_UpdatingTagThatIsNotFoundById_Then_NotFoundExceptionIsThrown() {
        when(tagDao.existsByName(name)).thenReturn(false);
        when(tagDao.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.update(id, requestDto));

        verify(tagDao, times(1)).existsByName(any());
        verify(tagDao, times(1)).findById(any());
        verifyNoMoreInteractions(tagDao, mapper);
    }

    @Test
    void Given_TagId_When_DeletingExistingTag_Then_TagIsDeleted() {
        when(tagDao.existsById(id)).thenReturn(true);
        doNothing().when(tagDao).deleteById(id);
        doNothing().when(giftCertificateTagDao).deleteByTagId(id);

        service.deleteById(id);

        verify(tagDao, times(1)).existsById(any());
        verify(tagDao, times(1)).deleteById(any());
        verify(giftCertificateTagDao, only()).deleteByTagId(any());
        verifyNoMoreInteractions(tagDao);
    }

    @Test
    void Given_TagId_When_DeletingNotExistingTag_Then_NotFoundExceptionIsThrown() {
        when(tagDao.existsById(id)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> service.deleteById(id));

        verify(tagDao, only()).existsById(any());
        verifyNoInteractions(giftCertificateTagDao);
    }
}
