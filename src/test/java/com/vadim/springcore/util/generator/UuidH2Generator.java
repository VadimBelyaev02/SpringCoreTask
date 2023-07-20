package com.vadim.springcore.util.generator;

import java.util.UUID;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;
import org.hibernate.id.UUIDGenerator;

public class UuidH2Generator {
    public static String generateUuid() {
        NoArgGenerator generator = Generators.randomBasedGenerator();
        return generator.generate().toString();
    }
}
