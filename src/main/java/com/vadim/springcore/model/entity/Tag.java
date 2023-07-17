package com.vadim.springcore.model.entity;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    private UUID id;

    private String name;
}
