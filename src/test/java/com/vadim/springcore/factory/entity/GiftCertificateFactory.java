package com.vadim.springcore.factory.entity;

import com.vadim.springcore.factory.ModelFactory;
import com.vadim.springcore.model.entity.GiftCertificate;
import lombok.NoArgsConstructor;

import static com.vadim.springcore.util.constants.GiftCertificateTestConstants.*;

//@With
@NoArgsConstructor(staticName = "getTagFactory")
//@AllArgsConstructor
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
