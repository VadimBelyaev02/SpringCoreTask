package com.vadim.springcore.factory.dto.request;
import com.vadim.springcore.util.constants.TagTestConstants;
import com.vadim.springcore.factory.ModelFactory;
import com.vadim.springcore.model.dto.request.TagRequestDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import static com.vadim.springcore.util.constants.GiftCertificateTestConstants.NAME;
//@With
@NoArgsConstructor(staticName = "getTagFactory")
//@AllArgsConstructor
public class TagRequestDtoFactory implements ModelFactory<TagRequestDto> {

    @Override
    public TagRequestDto getInstance() {
        return TagRequestDto.builder()
                .name(NAME)
                .build();
    }
}
