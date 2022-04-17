package com.example.myscheduler.service;

import com.example.myscheduler.domain.Plan;
import com.example.myscheduler.mapper.PlanMapper;
import com.example.myscheduler.util.YearMonthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Plan> monthlyPlan = getMonthlyPlan(yearMonth);
        Map<Integer, List<Plan>> monthlySchedule = new HashMap<>();
        for(int day = 1; day <= YearMonthUtil.countDays(yearMonth); day++) {
            // 日付とその日の予定を組みにしたMapを生成
            monthlySchedule.put(day, findPlansByDay(day, monthlyPlan));
        }
        return monthlySchedule;
    }

    /**
     * 指定された年月の予定をDBから取得する
     * @param yearMonth 年月。「2022-04」のような文字列形式
     * @return ひと月分の予定リスト
     */
    private List<Plan> getMonthlyPlan(String yearMonth) {
        int userId = 1;
        String firstDateOfTheMonth = yearMonth + "-01";
        String firstDateOfNextMonth = YearMonthUtil.getNextMonth(yearMonth) + "-01";
        return planMapper.selectWithPeriodAndUserId(firstDateOfTheMonth, firstDateOfNextMonth, userId);
    }

    /**
     * ひと月分の予定リストから指定された１日の予定リストを取得
     * @param day 指定日
     * @param monthlyPlan ひと月分の予定リスト
     * @return 指定日の予定リスト
     */
    private List<Plan> findPlansByDay(int day, List<Plan> monthlyPlan) {
        List<Plan> dailyPlan = new ArrayList<>();
        for(Plan plan : monthlyPlan) {
            /* ひと月分のリストから取り出した予定の日付が指定日と等しい場合、
               指定日の予定リストに加える */
            if(plan.getPlannedAt().getDayOfMonth() == day) {
                dailyPlan.add(plan);
            }
        }
        return dailyPlan;
    }

}













