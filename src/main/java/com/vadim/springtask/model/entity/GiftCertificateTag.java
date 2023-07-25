package com.vadim.springtask.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gift_certificates_tags")
public class GiftCertificateTag {

    @EmbeddedId
    private GiftCertificateTagId id;

}
