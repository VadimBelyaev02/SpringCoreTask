package com.vadim.springcore.util.constants;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@UtilityClass
public class GiftCertificateTestConstants {

    public static final UUID ID = UUID.fromString("ea1d28e4-2c1e-4815-ab11-6a02792d34b0");
    public static final String NAME = "name";
    public static final BigDecimal PRICE = BigDecimal.TEN;
    public static final String DESCRIPTION = "description";
    public static final Integer DURATION = 100;
    public static final Instant CREATE_DATE = Instant.MIN;
    public static final Instant LAST_UPDATE_DATE = Instant.MAX;
    public static final int NUMBER_OF_GIFT_CERTIFICATES = 3;

}
