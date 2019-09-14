package com.sohu.spaces.version.util;

import java.time.*;
import java.util.Date;

/**
 * java8中日期对象和java.util.Date转换工具
 *
 * @author wcy
 */
public class TimeUtils {

    /**
     * java.util.Date转LocalDateTime
     * @param date
     * @return
     */
    public LocalDateTime UDateToLocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * java.util.Date转LocalDate
     * @param date
     * @return
     */
    public LocalDate UDateToLocalDate(Date date){
        LocalDateTime localDateTime = UDateToLocalDateTime(date);
        return localDateTime.toLocalDate();
    }

    /**
     * java.util.Date转LocalTime
     * @param date
     * @return
     */
    public LocalTime UDateToLocalTime(Date date){
        LocalDateTime localDateTime = UDateToLocalDateTime(date);
        return localDateTime.toLocalTime();
    }

    /**
     * LocalDateTime转Date
     * @param localDateTime
     * @return
     */
    public Date LocalDateTimeToDate(LocalDateTime localDateTime){
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * LocalDate转Date
     * @param localDate
     * @return
     */
    public Date LocalDateToDate(LocalDate localDate){
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * LocalDateTime转Date
     * @param localTime
     * @return
     */
    public Date LocalTimeToDate(LocalTime localTime){
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        return LocalDateTimeToDate(localDateTime);
    }

}
