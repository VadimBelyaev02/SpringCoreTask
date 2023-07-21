package com.vadim.springcore.factory.dto.response;

import com.vadim.springcore.factory.ModelFactory;
import com.vadim.springcore.model.dto.response.TagResponseDto;
import lombok.NoArgsConstructor;

import static com.vadim.springcore.util.constants.TagTestConstants.ID;
import static com.vadim.springcore.util.constants.TagTestConstants.NAME;

@NoArgsConstructor(staticName = "getTagFactory")
public class TagResponseDtoFactory implements ModelFactory<TagResponseDto> {
    @Override
    public TagResponseDto getInstance() {
        return TagResponseDto.builder()
                .id(ID)
                .name(NAME)
                .build();
    }
}

