package com.vadim.springtask.model.criteria;

import com.vadim.springtask.model.criteria.enums.SortField;
import com.vadim.springtask.model.criteria.enums.SortType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GiftCertificateCriteria {

    private String tagName;
    private String partOfDescription;
    private String partOfName;
    private SortField firstSortBy;
    private SortField secondSortBy;
    private SortType firstSortType;
    private SortType secondSortType;


}
