package com.alexslo.responseAnalyzer.service;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class EmailResponseAnalyzerTest {

    private EmailResponseAnalyzer responseAnalyzer;
    private List<String> expectedResult;
    private final String FAKE_PATH = "src/main/resources/files/fake.txt";
    private final String FILE_PATH = "src/main/resources/files/input.txt";

    @Before
    public void init() {
        RecordParser parser = new RecordParser();
        RecordsAnalyzer analyzer = new RecordsAnalyzer();
        responseAnalyzer = new EmailResponseAnalyzer(parser, analyzer);
        expectedResult = Arrays.asList("83", "100", "-");
    }

    @Test
    public void analyzeFileTest() {
        List<String> result;

        result = responseAnalyzer.analyzeFile(FILE_PATH);

        assertEquals(expectedResult, result);
    }

    @Test(expected = NullPointerException.class)
    public void analyzeFileNullArgumentTest() {
        responseAnalyzer.analyzeFile(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void analyzeFileFakePathTest() {
        responseAnalyzer.analyzeFile(FAKE_PATH);
    }
}
