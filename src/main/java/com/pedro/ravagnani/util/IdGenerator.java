package com.pedro.ravagnani.util;

import java.util.UUID;

public interface IdGenerator {

    public static String generate() {
        return UUID.randomUUID().toString();
    }

}