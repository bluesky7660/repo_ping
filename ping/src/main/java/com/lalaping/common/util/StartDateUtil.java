package com.lalaping.common.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class StartDateUtil {
    // 공휴일 예시 (실제 공휴일 날짜 추가)
    private static final Set<LocalDate> HOLIDAYS = new HashSet<>();

    static {
        // 예시: 공휴일 추가
        HOLIDAYS.add(LocalDate.of(2024, 1, 1)); // 신정
        HOLIDAYS.add(LocalDate.of(2024, 3, 1)); // 삼일절
        HOLIDAYS.add(LocalDate.of(2024, 5, 5)); // 어린이날
        HOLIDAYS.add(LocalDate.of(2024, 6, 6)); // 현충일
        HOLIDAYS.add(LocalDate.of(2024, 10, 3)); // 한글날
        HOLIDAYS.add(LocalDate.of(2024, 12, 25)); // 성탄절
        // 추가적인 공휴일...
    }

    public static LocalDate getNextBusinessDate(LocalDate startDate, int daysToAdd) {
        LocalDate nextDate = startDate;
        int addedDays = 0;

        while (addedDays < daysToAdd) {
            nextDate = nextDate.plusDays(1);
            // 주말 및 공휴일 체크
            if (nextDate.getDayOfWeek() != DayOfWeek.SATURDAY &&
                nextDate.getDayOfWeek() != DayOfWeek.SUNDAY &&
                !HOLIDAYS.contains(nextDate)) {
                addedDays++;
            }
        }

        return nextDate;
    }
}
