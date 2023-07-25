package com.vadim.springtask.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
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

    @Column(name = "gift_certificate_id")
    private UUID giftCertificateId;

    @Column(name = "tag_id")
    private UUID tagId;
}
