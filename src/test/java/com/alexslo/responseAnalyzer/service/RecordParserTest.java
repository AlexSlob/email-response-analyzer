package com.alexslo.responseAnalyzer.service;

import com.alexslo.responseAnalyzer.entity.DateInterval;
import com.alexslo.responseAnalyzer.entity.Question;
import com.alexslo.responseAnalyzer.entity.Record;
import com.alexslo.responseAnalyzer.entity.RecordType;
import com.alexslo.responseAnalyzer.entity.ResponseType;
import com.alexslo.responseAnalyzer.entity.Service;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecordParserTest {

    private Record waitingTimeRecord;
    private Record queryRecord;
    private String waitingLine;
    private String queryLine;
    private RecordParser parser;

    @Before
    public void init() {
        parser = new RecordParser();
        waitingLine = "C 1.1 2.3.4 P 25.03.2012 87";
        queryLine = "D 1 2.3 P 25.03.2012-26.05.2012";
        initRecord();
    }

    @Test
    public void parseStringWaitingTimeRecordTest() {
        assertEquals(waitingTimeRecord, parser.parseString(waitingLine));
    }

    @Test
    public void parseStringQueryRecordTest() {
        assertEquals(queryRecord, parser.parseString(queryLine));
    }

    @Test(expected = NullPointerException.class)
    public void parseStringNullTest() {
        parser.parseString(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseStringEmptyStringTest() {
        parser.parseString(" ");
    }

    private void initRecord() {
        waitingTimeRecord = new Record(RecordType.WAITING_TIME,
                new Service(1, 1),
                new Question(2, 3, 4),
                ResponseType.FIRST_ANSWER,
                DateInterval.fromString("25.03.2012"),
                87);

        queryRecord = new Record(RecordType.QUERY,
                new Service(1),
                new Question(2, 3),
                ResponseType.FIRST_ANSWER,
                DateInterval.fromString("25.03.2012-26.05.2012"),
                null);
    }
}
