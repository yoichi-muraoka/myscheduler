package com.example.myscheduler.service;

import com.example.myscheduler.domain.Plan;
import com.example.myscheduler.mapper.PlanMapper;
import com.example.myscheduler.util.YearMonthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        for(int day = 1; day <= YearMonthUtil.countDays(yearMonth); day++) {
            monthlySchedule.put(day, null);
        }
        return monthlySchedule;
    }

}













