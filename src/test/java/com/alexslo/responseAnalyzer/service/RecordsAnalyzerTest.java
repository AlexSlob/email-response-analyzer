package com.alexslo.responseAnalyzer.service;

import com.alexslo.responseAnalyzer.entity.DateInterval;
import com.alexslo.responseAnalyzer.entity.Question;
import com.alexslo.responseAnalyzer.entity.Record;
import com.alexslo.responseAnalyzer.entity.RecordType;
import com.alexslo.responseAnalyzer.entity.ResponseType;
import com.alexslo.responseAnalyzer.entity.Service;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class RecordsAnalyzerTest {

    private List<Record> records;
    private List<String> expectedResult;
    private RecordsAnalyzer analyzer;

    @Before
    public void init() {
        analyzer = new RecordsAnalyzer();
        initRecords();
        initExpectedResult();
    }

    @Test
    public void analyzeRecordsTest() {
        List<String> result = analyzer.analyzeRecords(records);

        assertEquals(expectedResult, result);
    }

    @Test(expected = NullPointerException.class)
    public void analyzeRecordsNullArgumentTest() {
        analyzer.analyzeRecords(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void analyzeRecordsEmptyListArgument() {
        analyzer.analyzeRecords(Collections.emptyList());
    }

    private void initRecords() {
        records = new ArrayList<>();
        Record waitingTimeRecord1 =
                new Record(RecordType.WAITING_TIME,
                        new Service(1, 1),
                        new Question(2, 3, 4),
                        ResponseType.FIRST_ANSWER,
                        DateInterval.fromString("25.09.2012"), 25);

        Record waitingTimeRecord2 =
                new Record(RecordType.WAITING_TIME,
                        new Service(1, 4),
                        new Question(2, 3, 5),
                        ResponseType.FIRST_ANSWER,
                        DateInterval.fromString("30.09.2012"), 35);

        Record waitingTimeRecord3 =
                new Record(RecordType.WAITING_TIME,
                        new Service(2, 1),
                        new Question(3, 4, 5),
                        ResponseType.NEXT_ANSWER,
                        DateInterval.fromString("05.08.2012"), 100);

        Record queryRecord1 = new Record(RecordType.QUERY,
                new Service(1, null),
                new Question(2, null, null),
                ResponseType.FIRST_ANSWER,
                DateInterval.fromString("20.09.2012-30.11.2012"), null);

        Record queryRecord2 = new Record(RecordType.QUERY,
                new Service(2, 1),
                new Question(3, 4, null),
                ResponseType.NEXT_ANSWER,
                DateInterval.fromString("05.08.2012-30.08.2012"), null);

        records.add(waitingTimeRecord1);
        records.add(waitingTimeRecord2);
        records.add(waitingTimeRecord3);
        records.add(queryRecord1);
        records.add(queryRecord2);
    }

    private void initExpectedResult() {
        expectedResult = Arrays.asList("30", "100");
    }
}
