package com.vadim.springcore.model.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
