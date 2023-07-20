package com.vadim.springcore.factory.dto.request;

import com.vadim.springcore.factory.ModelFactory;
import com.vadim.springcore.model.dto.request.GiftCertificateRequestDto;
import lombok.NoArgsConstructor;

import static com.vadim.springcore.util.constants.GiftCertificateTestConstants.*;
//@With
@NoArgsConstructor(staticName = "getTagFactory")
//@AllArgsConstructor
public class GiftCertificateRequestDtoFactory implements ModelFactory<GiftCertificateRequestDto> {
    @Override
    public GiftCertificateRequestDto getInstance() {
        return GiftCertificateRequestDto.builder()
                .tags(TagRequestDtoFactory.getTagFactory().getInstanceList())
                .duration(DURATION)
                .description(DESCRIPTION)
                .price(PRICE)
                .name(NAME)
                .build();
    }
}
