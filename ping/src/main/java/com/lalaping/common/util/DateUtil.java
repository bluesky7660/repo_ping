package com.lalaping.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DateUtil {
    // 공휴일 예시 (실제 공휴일 날짜 추가)
    private static final Set<Date> HOLIDAYS = new HashSet<>();

    static {
        // 예시: 공휴일 추가
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        HOLIDAYS.add(createDate(currentYear, 1, 1)); // 신정
        HOLIDAYS.add(createDate(currentYear, 3, 1)); // 삼일절
        HOLIDAYS.add(createDate(currentYear, 5, 5)); // 어린이날
        HOLIDAYS.add(createDate(currentYear, 6, 6)); // 현충일
        HOLIDAYS.add(createDate(currentYear, 8, 15)); // 광복절
        HOLIDAYS.add(createDate(currentYear, 10, 3)); // 개천절
        HOLIDAYS.add(createDate(currentYear, 10, 9)); // 한글날
        HOLIDAYS.add(createDate(currentYear, 12, 25)); // 성탄절
        // 추가적인 공휴일...
    }

    // Date 생성 헬퍼 메소드
    private static Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); // 월은 0부터 시작
        return calendar.getTime();
    }

    public static Date getDeliveryDate(int daysToAdd) {
        Date nextDate = new Date(); // 오늘 날짜로 시작
        int addedDays = 0;
        Calendar calendar = Calendar.getInstance();

        while (addedDays < daysToAdd) {
            calendar.setTime(nextDate);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            nextDate = calendar.getTime();
            // 주말 및 공휴일 체크
            if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY &&
                calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY &&
                !HOLIDAYS.contains(nextDate)) {
                addedDays++;
            }
        }

        return nextDate;
    }
}
