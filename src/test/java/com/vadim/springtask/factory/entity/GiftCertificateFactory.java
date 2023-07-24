package com.vadim.springtask.factory.entity;

import com.vadim.springtask.factory.ModelFactory;
import com.vadim.springtask.model.entity.GiftCertificate;
import lombok.NoArgsConstructor;

import static com.vadim.springtask.util.constants.GiftCertificateTestConstants.*;

@NoArgsConstructor(staticName = "getTagFactory")
public class GiftCertificateFactory implements ModelFactory<GiftCertificate> {
    @Override
    public GiftCertificate getInstance() {
        return GiftCertificate.builder()
                .id(ID)
                .name(NAME)
                .price(PRICE)
                .tags(TagFactory.getTagFactory().getInstanceList())
                .createDate(CREATE_DATE)
                .lastUpdateDate(LAST_UPDATE_DATE)
                .duration(DURATION)
                .description(DESCRIPTION)
                .build();
    }
}
