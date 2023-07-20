package com.vadim.springcore.util.generator;

import java.util.UUID;

public class UuidH2Generator {

    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
