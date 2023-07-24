package com.vadim.springtask.model.dto.mapper;

import com.vadim.springtask.model.dto.request.TagRequestDto;
import com.vadim.springtask.model.dto.response.TagResponseDto;
import com.vadim.springtask.model.entity.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public Tag toEntity(TagRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Tag.TagBuilder tag = Tag.builder();

        tag.name( dto.getName() );

        return tag.build();
    }

    @Override
    public TagResponseDto toResponseDto(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagResponseDto.TagResponseDtoBuilder tagResponseDto = TagResponseDto.builder();

        tagResponseDto.id( tag.getId() );
        tagResponseDto.name( tag.getName() );

        return tagResponseDto.build();
    }

    @Override
    public List<Tag> tagRequestDtoListToTagList(List<TagRequestDto> tagRequestDtos) {
        if ( tagRequestDtos == null ) {
            return null;
        }

        List<Tag> list = new ArrayList<Tag>( tagRequestDtos.size() );
        for ( TagRequestDto tagRequestDto : tagRequestDtos ) {
            list.add( toEntity( tagRequestDto ) );
        }

        return list;
    }

    @Override
    public List<TagResponseDto> tagListToTagResponseDtoList(List<Tag> tags) {
        if ( tags == null ) {
            return null;
        }

        List<TagResponseDto> list = new ArrayList<TagResponseDto>( tags.size() );
        for ( Tag tag : tags ) {
            list.add( toResponseDto( tag ) );
        }

        return list;
    }

    @Override
    public void updateTagFromDto(TagRequestDto requestDto, Tag tag) {
        if ( requestDto == null ) {
            return;
        }

        if ( requestDto.getName() != null ) {
            tag.setName( requestDto.getName() );
        }
    }
}
