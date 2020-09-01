package com.alexslo.responseAnalyzer.entity;

import java.util.Date;
import java.util.Objects;

import static com.alexslo.responseAnalyzer.util.DateUtil.parseDate;


public class DateInterval {

    private static final String DATE_SEPARATOR = "-";
    private final Date dateFrom;
    private final Date dateTo;

    public DateInterval(Date dateFrom) {
        this(dateFrom, null);
    }

    public DateInterval(Date dateFrom, Date dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public static DateInterval fromString(String value) {
        Objects.requireNonNull(value);
        String[] parts = value.split(DATE_SEPARATOR);

        if (parts.length > 2) {
            throw new IllegalArgumentException(
                    String.format("Malformed date interval value. Expected to contain 2 parts. Got %s", value));
        }

        return parts.length == 2 ?
                new DateInterval(parseDate(parts[0]), parseDate(parts[1])) :
                new DateInterval(parseDate(parts[0]));

    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public boolean matches(Date date) {
        return !(date.before(dateFrom) || date.after(dateTo));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateInterval that = (DateInterval) o;
        return Objects.equals(dateFrom, that.dateFrom) &&
                Objects.equals(dateTo, that.dateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateFrom, dateTo);
    }
}
