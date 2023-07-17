package com.vadim.springcore.model.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GiftCertificate {

//    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String name;
    private BigDecimal price;
    private Integer duration;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant createDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant lastUpdateDate;
    private String description;
    private List<Tag> tags;
}
