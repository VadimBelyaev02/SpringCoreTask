package com.vadim.springcore.factory.dto.response;

import com.vadim.springcore.factory.ModelFactory;
import com.vadim.springcore.model.dto.response.GiftCertificateResponseDto;
import lombok.NoArgsConstructor;

import static com.vadim.springcore.util.constants.GiftCertificateTestConstants.*;
//@With
@NoArgsConstructor(staticName = "getTagFactory")
//@AllArgsConstructor
public class GiftCertificateResponseDtoFactory implements ModelFactory<GiftCertificateResponseDto> {
    @Override
    public GiftCertificateResponseDto getInstance() {
        return GiftCertificateResponseDto.builder()
                .id(ID)
                .name(NAME)
                .duration(DURATION)
                .description(DESCRIPTION)
                .createDate(CREATE_DATE)
                .lastUpdateDate(LAST_UPDATE_DATE)
                .price(PRICE)
                .tags(TagResponseDtoFactory.getTagFactory().getInstanceList())
                .build();
    }
}
