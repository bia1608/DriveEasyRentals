package org.driveeasy.driveeasyrentals.util;

import java.time.LocalDate;

public final class DateUtils {

    private DateUtils() {
    }

    public static boolean isInvalidRange(LocalDate start, LocalDate end) {
        return start == null || end == null || !start.isBefore(end);
    }

    public static boolean overlaps(LocalDate start1, LocalDate end1,
                                   LocalDate start2, LocalDate end2) {
        // ranges [start1, end1) and [start2, end2) overlap if start1 < end2 && start2 < end1
        return start1.isBefore(end2) && start2.isBefore(end1);
    }
}
