package com.example.myscheduler.service;

import com.example.myscheduler.domain.Plan;
import com.example.myscheduler.mapper.PlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private PlanMapper planMapper;

    /**
     * 指定された月の月間予定を取得
     *
     * @param yearMonth 年月。「2022-04」のような文字列形式
     * @return 1日から月末までの予定。Map<日, その日の予定リスト>
     */
    @Override
    public Map<Integer, List<Plan>> getMonthly(String yearMonth) {
        Map<Integer, List<Plan>> monthlySchedule = new HashMap<>();
        for(int day = 1; day <= countDays(yearMonth); day++) {
            monthlySchedule.put(day, null);
        }
        return monthlySchedule;
    }

    /**
     * 指定された月の日数を返す
     * @param yearMonth 年月。「2022-04」のような文字列形式
     * @return 日数
     */
    private int countDays(String yearMonth) {
        // 指定された年月の初日をセット
        int[] yearAndMonth = getYearAndMonth(yearMonth);
        LocalDate ld = LocalDate.of(yearAndMonth[0], yearAndMonth[1], 1);
        // その月の末日を設定
        ld = ld.with(TemporalAdjusters.lastDayOfMonth());
        return ld.getDayOfMonth();
    }

    /**
     * 年月を 年と月 に分割
     * @param yearMonth 年月。「2022-04」のような文字列形式
     * @return [0]年, [1]月
     */
    private int[] getYearAndMonth(String yearMonth) {
        String[] strYearAndMonth = yearMonth.split("-");
        int[] yearAndMonth = {
                Integer.parseInt(strYearAndMonth[0]),
                Integer.parseInt(strYearAndMonth[1])
        };
        return yearAndMonth;
    }
}













