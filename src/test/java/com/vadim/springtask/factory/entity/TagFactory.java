package com.vadim.springtask.factory.entity;

import com.vadim.springtask.factory.ModelFactory;
import com.vadim.springtask.model.entity.Tag;
import lombok.NoArgsConstructor;

import static com.vadim.springtask.util.constants.TagTestConstants.ID;
import static com.vadim.springtask.util.constants.TagTestConstants.NAME;

@NoArgsConstructor(staticName = "getTagFactory")
public class TagFactory implements ModelFactory<Tag> {

    @Override
    public Tag getInstance() {
        return Tag.builder()
                .id(ID)
                .name(NAME)
                .build();
    }
}
