package com.vadim.springtask.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "purchase_time")
    private Instant purchaseTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gift_certificate_id", referencedColumnName = "id")
    private GiftCertificate giftCertificate;

}