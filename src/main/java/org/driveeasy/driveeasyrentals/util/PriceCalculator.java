package org.driveeasy.driveeasyrentals.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class PriceCalculator {

    private PriceCalculator() {
    }

    public static BigDecimal calculateTotal(LocalDate start,
                                            LocalDate end,
                                            BigDecimal dailyRate) {
        long days = ChronoUnit.DAYS.between(start, end);
        return dailyRate.multiply(BigDecimal.valueOf(days));
    }
}
