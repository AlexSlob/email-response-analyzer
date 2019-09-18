package com.alexslo.responseAnalyzer.entity;

import java.util.Objects;

public class Record {

    private RecordType recordType;
    private Service service;
    private Question question;
    private ResponseType responseType;
    private DateInterval dateInterval;
    private Integer value;

    public Record(RecordType recordType, Service service,
                  Question question, ResponseType responseType,
                  DateInterval dateInterval, Integer value) {
        this.recordType = recordType;
        this.service = service;
        this.question = question;
        this.responseType = responseType;
        this.dateInterval = dateInterval;
        this.value = value;
    }

    public RecordType getRecordType() {
        return recordType;
    }

    public Service getService() {
        return service;
    }

    public Question getQuestion() {
        return question;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public DateInterval getDateInterval() {
        return dateInterval;
    }

    public Integer getValue() {
        return value;
    }

    public boolean matches(Record record) {
        return this.getService().matches(record.getService()) &&
                this.getQuestion().matches(record.getQuestion()) &&
                this.getResponseType() == record.getResponseType() &&
                this.getDateInterval().matches(record.getDateInterval().getDateFrom());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return recordType == record.recordType &&
                Objects.equals(service, record.service) &&
                Objects.equals(question, record.question) &&
                responseType == record.responseType &&
                Objects.equals(dateInterval, record.dateInterval) &&
                Objects.equals(value, record.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordType, service, question, responseType, dateInterval, value);
    }
}
