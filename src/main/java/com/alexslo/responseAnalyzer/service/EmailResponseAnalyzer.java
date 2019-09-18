package com.alexslo.responseAnalyzer.service;

import com.alexslo.responseAnalyzer.entity.Record;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EmailResponseAnalyzer {

    private final RecordParser parser;
    private final RecordsAnalyzer analyzer;

    public EmailResponseAnalyzer(RecordParser parser, RecordsAnalyzer analyzer) {
        this.parser = parser;
        this.analyzer = analyzer;
    }

    /**
     * Analyzes input file and returns calculated result from analyzed Records
     *
     * @param filePath
     * @return
     */
    public List<String> analyzeFile(String filePath) {
        Objects.requireNonNull(filePath);

        List<String> lines = analyzeFileToList(filePath);

        List<Record> records = lines.stream().
                map(parser::parseString).
                collect(Collectors.toList());

        return analyzer.analyzeRecords(records);
    }

    /**
     * Converts file data into list of String values. Verifies that file is readable and all needed part are present
     *
     * @param filePath
     * @return
     */
    private List<String> analyzeFileToList(String filePath) {
        File inputFile = new File(filePath);

        if (Files.notExists(inputFile.toPath()) || !Files.isReadable(inputFile.toPath())) {
            throw new IllegalArgumentException("File doesn't exist or is not readable");
        }

        List<String> lines = null;
        try {
            lines = Files.readAllLines(inputFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Corrupted file. Unable to read lines");
        }

        int numberOfLines = Integer.parseInt(lines.get(0));
        int linesSize = lines.size() - 1;

        if (linesSize != numberOfLines)
            throw new IllegalArgumentException(
                    String.format("Corrupted file. Expected to contain %d lines. Got %d", numberOfLines, linesSize));

        lines.remove(0);

        return lines;
    }
}
