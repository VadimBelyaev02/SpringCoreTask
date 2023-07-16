package com.vadim.springcore.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GiftCertificateTagId implements Serializable {

    private UUID giftCertificateId;
    private UUID tagId;
}
