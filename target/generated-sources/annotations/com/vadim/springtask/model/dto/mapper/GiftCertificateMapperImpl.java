package com.vadim.springtask.model.dto.mapper;

import com.vadim.springtask.model.dto.request.GiftCertificateRequestDto;
import com.vadim.springtask.model.dto.response.GiftCertificateResponseDto;
import com.vadim.springtask.model.entity.GiftCertificate;
import com.vadim.springtask.model.entity.Tag;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class GiftCertificateMapperImpl implements GiftCertificateMapper {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public GiftCertificate toEntity(GiftCertificateRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        GiftCertificate.GiftCertificateBuilder giftCertificate = GiftCertificate.builder();

        giftCertificate.name( dto.getName() );
        giftCertificate.price( dto.getPrice() );
        giftCertificate.duration( dto.getDuration() );
        giftCertificate.description( dto.getDescription() );
        giftCertificate.tags( tagMapper.tagRequestDtoListToTagList( dto.getTags() ) );

        return giftCertificate.build();
    }

    @Override
    public GiftCertificateResponseDto toResponseDto(GiftCertificate giftCertificate) {
        if ( giftCertificate == null ) {
            return null;
        }

        GiftCertificateResponseDto.GiftCertificateResponseDtoBuilder giftCertificateResponseDto = GiftCertificateResponseDto.builder();

        giftCertificateResponseDto.id( giftCertificate.getId() );
        giftCertificateResponseDto.name( giftCertificate.getName() );
        giftCertificateResponseDto.price( giftCertificate.getPrice() );
        giftCertificateResponseDto.duration( giftCertificate.getDuration() );
        giftCertificateResponseDto.createDate( giftCertificate.getCreateDate() );
        giftCertificateResponseDto.lastUpdateDate( giftCertificate.getLastUpdateDate() );
        giftCertificateResponseDto.description( giftCertificate.getDescription() );
        giftCertificateResponseDto.tags( tagMapper.tagListToTagResponseDtoList( giftCertificate.getTags() ) );

        return giftCertificateResponseDto.build();
    }

    @Override
    public void updateGiftCertificateFromDto(GiftCertificateRequestDto requestDto, GiftCertificate giftCertificate) {
        if ( requestDto == null ) {
            return;
        }

        if ( requestDto.getPrice() != null ) {
            giftCertificate.setPrice( requestDto.getPrice() );
        }
        if ( requestDto.getName() != null ) {
            giftCertificate.setName( requestDto.getName() );
        }
        if ( requestDto.getDuration() != null ) {
            giftCertificate.setDuration( requestDto.getDuration() );
        }
        if ( requestDto.getDescription() != null ) {
            giftCertificate.setDescription( requestDto.getDescription() );
        }
        if ( giftCertificate.getTags() != null ) {
            List<Tag> list = tagMapper.tagRequestDtoListToTagList( requestDto.getTags() );
            if ( list != null ) {
                giftCertificate.getTags().clear();
                giftCertificate.getTags().addAll( list );
            }
            else {
                giftCertificate.setTags( null );
            }
        }
        else {
            List<Tag> list = tagMapper.tagRequestDtoListToTagList( requestDto.getTags() );
            if ( list != null ) {
                giftCertificate.setTags( list );
            }
        }
    }
}
