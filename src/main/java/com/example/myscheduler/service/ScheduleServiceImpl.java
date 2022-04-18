package com.example.myscheduler.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myscheduler.domain.Plan;
import com.example.myscheduler.mapper.PlanMapper;
import com.example.myscheduler.util.YearMonthUtil;

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
     * 指定された日の予定を取得
     * @param yearMonth　年月。「2022-04」のような文字列形式
     * @param day 日
     * @return １日分の予定
     */
    @Override
    public List<Plan> getDaily(String yearMonth, String day) {
        int userId = 1; //TODO セッションからユーザーIDを取得
        // 年月と日を繋げて、yyyy-MM-ddの文字列形式に変換
        String date = yearMonth + "-";
        // 日が1桁(1字)の場合、頭に0を付ける
        date += day.length() == 1 ? "0" + day : day;
        return planMapper.selectWithPlannedDateAndUserId(date, userId);
    }
    
    /**
     * 予定を追加
     * @param plan　予定の日付、タイトル、内容、開始～終了時間
     */
    @Override
    public void addPlan(Plan plan) {
    	int userId = 1; //TODO セッションからユーザーIDを取得
    	plan.setUserId(userId);
    	// 改行文字を<br>に変換
    	String description = plan.getDescription().replaceAll("\n", "<br>");
    	plan.setDescription(description);
    	planMapper.insert(plan);
    }

    /**
     * 指定された予定を削除
     * @param planId 削除する予定のID
     */
    @Override
    public void deletePlan(String planId) {
        int userId = 1; //TODO セッションからユーザーIDを取得
        planMapper.deletePlan(Integer.parseInt(planId), userId);
    }

    /**
     * 指定された年月の予定をDBから取得する
     * @param yearMonth 年月。「2022-04」のような文字列形式
     * @return ひと月分の予定リスト
     */
    private List<Plan> getMonthlyPlan(String yearMonth) {
        int userId = 1; //TODO セッションからユーザーIDを取得
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













