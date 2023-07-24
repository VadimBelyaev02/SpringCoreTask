package com.vadim.springtask.util.generator;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;

public class UuidH2Generator {
    public static String generateUuid() {
        NoArgGenerator generator = Generators.randomBasedGenerator();
        return generator.generate().toString();
    }
}
