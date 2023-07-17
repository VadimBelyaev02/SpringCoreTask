package com.vadim.springcore.model.criteria.enums;

public enum SortType {
    ASC("ASC"),
    DESC("DESC");

    private final String sortType;

    SortType(String sortType) {
        this.sortType = sortType;
    }
}
