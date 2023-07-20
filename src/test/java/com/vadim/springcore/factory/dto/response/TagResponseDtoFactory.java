package com.vadim.springcore.factory.dto.response;

import com.vadim.springcore.factory.ModelFactory;
import com.vadim.springcore.model.dto.response.TagResponseDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.UUID;

import static com.vadim.springcore.util.constants.TagTestConstants.ID;
import static com.vadim.springcore.util.constants.TagTestConstants.NAME;
//@With
@NoArgsConstructor(staticName = "getTagFactory")
//@AllArgsConstructor
public class TagResponseDtoFactory implements ModelFactory<TagResponseDto> {
    @Override
    public TagResponseDto getInstance() {
        return TagResponseDto.builder()
                .id(ID)
                .name(NAME)
                .build();
    }
}

