package uk.co.payr.payrusersapi.user.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneId;

@UtilityClass
public class DateUtils {

    public static LocalDateTime now() {
        return LocalDateTime.now(ZoneId.of("UTC"));
    }

    public static String nowAsString() {
        return now().toString();
    }
}
