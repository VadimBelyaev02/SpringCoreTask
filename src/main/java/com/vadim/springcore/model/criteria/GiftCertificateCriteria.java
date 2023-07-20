package com.vadim.springcore.model.criteria;

import com.vadim.springcore.model.criteria.enums.SortField;
import com.vadim.springcore.model.criteria.enums.SortType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GiftCertificateCriteria {

    private String tagName;
    private String partOfDescription;
    private String partOfTagName;
    private SortField firstSortBy;
    private SortField secondSortBy;
    private SortType firstSortType;
    private SortType secondSortType;


}
