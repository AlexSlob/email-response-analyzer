package com.alexslo.responseAnalyzer.service;

import com.alexslo.responseAnalyzer.entity.DateInterval;
import com.alexslo.responseAnalyzer.entity.Question;
import com.alexslo.responseAnalyzer.entity.Record;
import com.alexslo.responseAnalyzer.entity.RecordType;
import com.alexslo.responseAnalyzer.entity.ResponseType;
import com.alexslo.responseAnalyzer.entity.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RecordParser {

    /**
     * Return  Record  builded from parsed String
     */
    public Record parseString(String line) {
        Objects.requireNonNull(line);

        return buildRecord(convertLineToParts(line));
    }

    /**
     * Separates line to parts by 'space'
     */
    private List<String> convertLineToParts(String line) {
        return Arrays.asList(line.split(" "));
    }

    private Record buildRecord(List<String> parts) {
        Objects.requireNonNull(parts);

        if (parts.size() < 5) {
            throw new IllegalArgumentException("Not enough parts");
        }

        Integer value = getValueFromParts(parts);

        return createRecord(parts, value);
    }

    /**
     * Build`s Record from given parsed fragments
     */
    private Record createRecord(List<String> parts, Integer value) {
        int recordTypeIndex = 0;
        int serviceIndex = 1;
        int questionIndex = 2;
        int responseIndex = 3;
        int dateIndex = 4;

        return new Record(
                RecordType.fromCharacter(parts.get(recordTypeIndex)),
                Service.fromString(parts.get(serviceIndex)),
                Question.fromString(parts.get(questionIndex)),
                ResponseType.fromCharacter(parts.get(responseIndex)),
                DateInterval.fromString(parts.get(dateIndex)),
                value);
    }

    /**
     * Verifies that there are enough parts to get int value for WaitingTime Record
     */
    private Integer getValueFromParts(List<String> parts) {
        int valueIndex = 5;
        int partsSize = 6;

        return parts.size() == partsSize ? Integer.parseInt(parts.get(valueIndex)) : null;
    }
}
