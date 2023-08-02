package com.vadim.springtask.model.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public class PageResponseDto<T> {

    private Integer pageNumber;

    private Integer size;

    private Integer elementsAmount;

    private List<T> content;

}
