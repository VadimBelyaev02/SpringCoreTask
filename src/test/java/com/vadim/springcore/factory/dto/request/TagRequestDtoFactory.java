package com.vadim.springcore.factory.dto.request;

import com.vadim.springcore.factory.ModelFactory;
import com.vadim.springcore.model.dto.request.TagRequestDto;
import lombok.NoArgsConstructor;

import static com.vadim.springcore.util.constants.GiftCertificateTestConstants.NAME;

@NoArgsConstructor(staticName = "getTagFactory")
public class TagRequestDtoFactory implements ModelFactory<TagRequestDto> {

    @Override
    public TagRequestDto getInstance() {
        return TagRequestDto.builder()
                .name(NAME)
                .build();
    }
}
