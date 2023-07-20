package com.vadim.springcore.factory.entity;

import com.vadim.springcore.factory.ModelFactory;
import com.vadim.springcore.model.entity.Tag;
import lombok.NoArgsConstructor;

import static com.vadim.springcore.util.constants.TagTestConstants.ID;
import static com.vadim.springcore.util.constants.TagTestConstants.NAME;

//@With
@NoArgsConstructor(staticName = "getTagFactory")
//@AllArgsConstructor
public class TagFactory implements ModelFactory<Tag> {

    @Override
    public Tag getInstance() {
        return Tag.builder()
                .id(ID)
                .name(NAME)
                .build();
    }
}
