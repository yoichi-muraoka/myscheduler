package com.example.myscheduler.service;

import java.util.List;
import java.util.Map;

import com.example.myscheduler.domain.Plan;

public interface ScheduleService {

    /**
     * 指定された月の月間予定を取得
     * @param yearMonth　年月。「2022-04」のような文字列形式
     * @return 1日から月末までの予定。Map<日, その日の予定リスト>
     */
    Map<Integer, List<Plan>> getMonthly(String yearMonth);

    /**
     * 指定された日の予定を取得
     * @param yearMonth　年月。「2022-04」のような文字列形式
     * @param day 日
     * @return １日分の予定
     */
    List<Plan> getDaily(String yearMonth, String day);
    
    /**
     * 予定を追加
     * @param plan　予定の日付、タイトル、内容、開始～終了時間
     */
    void addPlan(Plan plan);

    /**
     * 指定された予定を削除
     * @param planId 削除する予定のID
     */
    void deletePlan(String planId);

}
