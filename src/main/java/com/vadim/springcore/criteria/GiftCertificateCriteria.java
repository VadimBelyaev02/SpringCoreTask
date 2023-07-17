package com.vadim.springcore.criteria;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GiftCertificateCriteria {

    private String tagName;
    private String partOfDescription;
    private String partOfTagName;
    private String sortByDate;
    private String sortByName;
}
