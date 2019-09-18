package com.alexslo.responseAnalyzer.entity;

public enum RecordType {

    WAITING_TIME("C"),
    QUERY("D");

    private final String character;

    RecordType(String character) {
        this.character = character;
    }

    public static RecordType fromCharacter(String character) {
        for (RecordType recordType : values()) {
            if (recordType.character.equals(character)) {
                return recordType;
            }
        }

        throw new IllegalArgumentException(String.format("Failed to resolve record type from character %s", character));
    }
}
