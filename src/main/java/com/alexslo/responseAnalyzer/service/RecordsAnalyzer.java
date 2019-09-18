package com.alexslo.responseAnalyzer.service;

import com.alexslo.responseAnalyzer.entity.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.alexslo.responseAnalyzer.entity.RecordType.WAITING_TIME;


public class RecordsAnalyzer {

    /**
     * Selects the appropriate records that match certain Query Record & returns list of processed data
     *
     * @param records
     * @return
     */
    public List<String> analyzeRecords(List<Record> records) {
        Objects.requireNonNull(records);

        if (records.isEmpty()) {
            throw new IllegalArgumentException("Empty records");
        }

        List<Record> waitingTimeRecords = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        for (Record record : records) {
            if (record.getRecordType() == WAITING_TIME) {
                waitingTimeRecords.add(record);
            } else {
                Integer averageWaitingTime = calculateAverageWaitingTime(waitingTimeRecords, record);
                result.add(averageWaitingTime);
            }
        }
        return convertResultToString(result);
    }

    /**
     * Sums all values from WaitingTime Records that match give Query Record & returns sum divided to their quantity
     *
     * @param queryRecord
     * @return
     */
    private Integer calculateAverageWaitingTime(List<Record> waitingTimeRecords, Record queryRecord) {
        List<Record> matchingWaitingTimeLines = getMatchingWaitingTimeLines(waitingTimeRecords, queryRecord);

        if (matchingWaitingTimeLines.isEmpty()) {
            return null;
        }

        int sum = matchingWaitingTimeLines.stream()
                .mapToInt(Record::getValue)
                .sum();

        return sum / matchingWaitingTimeLines.size();
    }

    /**
     * Filters WaitingTime Records that match required Query Record
     *
     * @param queryRecord
     * @return Record list
     */

    private List<Record> getMatchingWaitingTimeLines(List<Record> waitingTimeRecords, Record queryRecord) {
        Objects.requireNonNull(queryRecord);

        return waitingTimeRecords.stream()
                .filter(queryRecord::matches)
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Converts final result into String value. Replaces null values to ' - ' to match expected output
     *
     * @param list of Integer values
     * @return String values as list
     */

    private List<String> convertResultToString(List<Integer> list) {
        Objects.requireNonNull(list);

        List<String> result = new ArrayList<>();

        for (Integer value : list) {

            if (value != null) {
                result.add(value.toString());
            } else {
                result.add("-");
            }
        }
        return result;
    }
}
