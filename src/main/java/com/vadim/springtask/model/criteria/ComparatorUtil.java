package com.vadim.springtask.model.criteria;

import com.vadim.springtask.model.criteria.enums.SortField;
import com.vadim.springtask.model.criteria.enums.SortType;
import com.vadim.springtask.model.entity.GiftCertificate;

import java.util.Comparator;
import java.util.Objects;

public class ComparatorUtil {

    public static Comparator<GiftCertificate> getComparator(SortField sortField, SortType sortType) {
        Comparator<GiftCertificate> comparator;
        if (Objects.equals(sortField, SortField.CREATE_DATE)) {
            comparator = Comparator.comparing(GiftCertificate::getCreateDate);
        }
        else if (Objects.equals(sortField, SortField.NAME)) {
            comparator = Comparator.comparing(GiftCertificate::getName);
        }
        else {
            comparator = Comparator.comparingInt(a -> 0);
        }

        if (Objects.equals(sortType, SortType.DESC)) {
            comparator = comparator.reversed();
        }
        return comparator;
    }
}
