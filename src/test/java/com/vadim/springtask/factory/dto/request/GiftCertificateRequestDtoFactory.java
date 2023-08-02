package com.vadim.springtask.factory.dto.request;

import com.vadim.springtask.factory.ModelFactory;
import com.vadim.springtask.model.dto.request.GiftCertificateRequestDto;
import lombok.NoArgsConstructor;

import static com.vadim.springtask.util.constants.GiftCertificateTestConstants.*;

@NoArgsConstructor(staticName = "getTagFactory")
public class GiftCertificateRequestDtoFactory implements ModelFactory<GiftCertificateRequestDto> {
    @Override
    public GiftCertificateRequestDto getInstance() {
        return GiftCertificateRequestDto.builder()
                .duration(DURATION)
                .description(DESCRIPTION)
                .price(PRICE)
                .name(NAME)
                .build();
    }
}
