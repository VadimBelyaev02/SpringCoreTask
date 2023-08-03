package com.vadim.springtask.model.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponseDto<T> {

    private Integer pageNumber;

    private Integer size;

    private Integer elementsAmount;

    private List<T> content;

}
