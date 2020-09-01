package com.alexslo.responseAnalyzer;

import com.alexslo.responseAnalyzer.service.EmailResponseAnalyzer;
import com.alexslo.responseAnalyzer.service.RecordParser;
import com.alexslo.responseAnalyzer.service.RecordsAnalyzer;

public class Application {

    private static final String FILE_PATH = "src/main/resources/files/input.txt";

    public static void main(String[] args) {
        EmailResponseAnalyzer responseAnalyzer = new EmailResponseAnalyzer(new RecordParser(), new RecordsAnalyzer());
        responseAnalyzer.analyzeFile(FILE_PATH).forEach(System.out::println);
    }
}
