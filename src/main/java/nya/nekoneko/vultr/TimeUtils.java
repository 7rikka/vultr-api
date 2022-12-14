package nya.nekoneko.vultr;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * 时间处理工具类
 *
 * @author Ho
 */
public class TimeUtils {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String currentDate() {
        return LocalDate.now().format(DATE_FORMAT);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String currentTime() {
        return LocalTime.now().format(TIME_FORMAT);
    }

    /**
     * 获取当前日期时间
     *
     * @return
     */
    public static String currentDateTime() {
        return LocalDateTime.now().format(DATETIME_FORMAT);
    }

    /**
     * 转换为日期时间格式
     *
     * @param localDateTime
     * @return
     */
    public static String toDateTime(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        return localDateTime.format(DATETIME_FORMAT);
    }

    /**
     * 转换为日期格式
     *
     * @param localDateTime
     * @return
     */
    public static String toDate(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        return localDateTime.format(DATE_FORMAT);
    }

    /**
     * 转换为时间格式
     *
     * @param localDateTime
     * @return
     */
    public static String toTime(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        return localDateTime.format(TIME_FORMAT);
    }

    public static LocalDateTime toBeijingTime(String time) {
        String[] split = time.split("\\+");
        time = split[0].replace("T", " ");
        int offset = Integer.parseInt(split[1].split(":")[0]);
        LocalDateTime localDateTime = LocalDateTime.parse(time, DATETIME_FORMAT);
        ZonedDateTime zonedtime = localDateTime.atZone(ZoneId.from(ZoneOffset.ofHours(offset)));
        ZonedDateTime converted = zonedtime.withZoneSameInstant(ZoneOffset.ofHours(8));
        return converted.toLocalDateTime();
    }
}