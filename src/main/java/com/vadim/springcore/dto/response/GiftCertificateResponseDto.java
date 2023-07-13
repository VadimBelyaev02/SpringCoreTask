package com.vadim.springcore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GiftCertificateResponseDto {

    private UUID id;

    private String name;

    private BigDecimal price;

    private Integer duration;

    private Instant createDate;

    private Instant lastUpdateDate;
}
