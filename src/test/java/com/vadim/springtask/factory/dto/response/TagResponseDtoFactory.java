package com.vadim.springtask.factory.dto.response;

import com.vadim.springtask.factory.ModelFactory;
import com.vadim.springtask.model.dto.response.TagResponseDto;
import lombok.NoArgsConstructor;

import static com.vadim.springtask.util.constants.TagTestConstants.ID;
import static com.vadim.springtask.util.constants.TagTestConstants.NAME;

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

