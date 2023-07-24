package com.vadim.springtask.factory.dto.response;

import com.vadim.springtask.factory.ModelFactory;
import com.vadim.springtask.model.dto.response.GiftCertificateResponseDto;
import lombok.NoArgsConstructor;

import static com.vadim.springtask.util.constants.GiftCertificateTestConstants.*;

@NoArgsConstructor(staticName = "getTagFactory")
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
