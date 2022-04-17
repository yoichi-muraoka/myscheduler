package com.example.myscheduler.util;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class YearMonthUtil {

    /**
     * 指定された月の日数を返す
     * @param yearMonth 年月。「2022-04」のような文字列形式
     * @return 日数
     */
    public static int countDays(String yearMonth) {
        // java.time.YearMonthを使用し、月末日を算出
        YearMonth ym = YearMonth.parse(yearMonth);
        return ym.lengthOfMonth();
    }

    /**
     * 翌月を文字列で返す
     * @param yearMonth 年月。「2022-04」のような文字列形式
     * @return 翌月。「2022-05」のような文字列形式
     */
    public static String getNextMonth(String yearMonth) {
        YearMonth ym = YearMonth.parse(yearMonth).plusMonths(1);
        return ym.format(DateTimeFormatter.ofPattern("yyyy-MM"));
    }

    /**
     * 年月を 年と月 に分割
     * @param yearMonth 年月。「2022-04」のような文字列形式
     * @return [0]年, [1]月
     */
    public static int[] getYearAndMonth(String yearMonth) {
        String[] strYearAndMonth = yearMonth.split("-");
        int[] yearAndMonth = {
                Integer.parseInt(strYearAndMonth[0]),
                Integer.parseInt(strYearAndMonth[1])
        };
        return yearAndMonth;
    }

}
