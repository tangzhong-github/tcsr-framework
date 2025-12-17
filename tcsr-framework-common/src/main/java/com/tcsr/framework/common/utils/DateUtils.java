package com.tcsr.framework.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 *
 * @author tangzhong
 * @date   2025-08-27 15:16
 * @since  V1.0.0.0
 */
public class DateUtils {

    private DateUtils(){}

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static LocalDate getLocalDate(){
        return LocalDate.now();
    }

    public static String formateLocalDateNow(){
        return formateLocalDateNow(YYYY_MM_DD);
    }

    public static String formateLocalDateNow(String pattern){
        return formateLocalDate(getLocalDate(), pattern);
    }

    public static String formateLocalDate(LocalDate localDate) {
        return formateLocalDate(localDate, YYYY_MM_DD);
    }

    public static String formateLocalDate(LocalDate localDate, String pattern) {
        return Optional.ofNullable(localDate).map(e->e.format(DateTimeFormatter.ofPattern(pattern))).orElse(null);
    }

    public static LocalDate parseLocalDate(String localDateStr){
        return parseLocalDate(localDateStr, YYYY_MM_DD);
    }

    public static LocalDate parseLocalDate(String localDateStr, String pattern){
        return LocalDate.parse(localDateStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime getLocalDateTime(){
        return LocalDateTime.now();
    }

    public static String getLocalDateTimeStr(){
        return getLocalDateTimeStr(YYYY_MM_DD_HH_MM_SS);
    }

    public static String getLocalDateTimeStr(String pattern){
        return getLocalDateTime().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parseLocalDateTime(String localDateTimeStr){
        return parseLocalDateTime(localDateTimeStr, YYYY_MM_DD_HH_MM_SS);
    }

    public static LocalDateTime parseLocalDateTime(String localDateTimeStr, String pattern){
        return LocalDateTime.parse(localDateTimeStr, DateTimeFormatter.ofPattern(pattern));
    }

}