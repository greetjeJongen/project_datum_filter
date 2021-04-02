package domain.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;

public class MyTimeStamp {

    /**
     * Formats the given timestamp to string
     *
     * @param timestamp
     * @return formatted timestamp as DD-MM-YYYY hh:mm
     */
    public static String formatTimeStamp(Timestamp timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm");
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        return localDateTime.format(formatter);
    }

    /**
     * Formats the date of the given timestamp to string
     * @param timestamp
     * @return date formatted as dd-MM-yyyy
     */
    public static String formatDate(Timestamp timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        return localDateTime.format(formatter);

    }

    /**
     * Formats the time of the given timestamp to string
     * @param timestamp
     * @return time formatted as hh:mm
     */
    public static String formatTime(Timestamp timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        return localDateTime.format(formatter);
    }


    /**
     * Converts date and time as string to Timestamp
     *
     * @param date formatted as YYYY-MM-DD
     * @param time formatted as hh:mm
     * @return corresponding timestamp
     * @throws DomainException when date or time have incorrect format
     */
    public static Timestamp getTimestampFromDateAndTimeAsString(String date, String time) {
        chekValidDate(date);
        checkValidTime(time);
        return Timestamp.valueOf(LocalDateTime.parse(date + "T" + time));
    }

    public static void checkValidTime(String time) {
        try {
            LocalTime localTime = LocalTime.parse(time);
        }
        catch (DateTimeParseException e) {
            throw new DomainException("Time has incorrect format. It should be hh:mm");
        }
    }

    public static void chekValidDate(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
        }
        catch (DateTimeParseException e) {
            throw new DomainException("Date has incorrect format. It should be yyyy-mm-dd");
        }
    }


}
