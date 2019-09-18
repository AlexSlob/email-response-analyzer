package com.alexslo.responseAnalyzer.entity;

public enum ResponseType {
    FIRST_ANSWER("P"),
    NEXT_ANSWER("N");

    private final String character;

    ResponseType(String character) {
        this.character = character;
    }

    public static ResponseType fromCharacter(String character) {
        for (ResponseType responseType : values()) {
            if (responseType.character.equals(character)) {
                return responseType;
            }
        }
        throw new IllegalArgumentException(String.format("Failed to resolve response type from character %s", character));
    }
}
