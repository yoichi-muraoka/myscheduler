package com.example.myscheduler.service;

import com.example.myscheduler.domain.Plan;

import java.util.List;
import java.util.Map;

public interface ScheduleService {

    /**
     * 指定された月の月間予定を取得
     * @param yearMonth　年月。「2022-04」のような文字列形式
     * @return 1日から月末までの予定。Map<日, その日の予定リスト>
     */
    Map<Integer, List<Plan>> getMonthly(String yearMonth);

}
