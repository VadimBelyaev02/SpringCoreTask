package com.vadim.springtask.factory.dto.request;

import com.vadim.springtask.factory.ModelFactory;
import com.vadim.springtask.model.dto.request.TagRequestDto;
import lombok.NoArgsConstructor;

import static com.vadim.springtask.util.constants.GiftCertificateTestConstants.NAME;

@NoArgsConstructor(staticName = "getTagFactory")
public class TagRequestDtoFactory implements ModelFactory<TagRequestDto> {

    @Override
    public TagRequestDto getInstance() {
        return TagRequestDto.builder()
                .name(NAME)
                .build();
    }
}
