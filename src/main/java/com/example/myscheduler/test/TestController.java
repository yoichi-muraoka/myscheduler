package com.example.myscheduler.test;

import com.example.myscheduler.domain.Plan;
import com.example.myscheduler.mapper.PlanMapper;
import com.example.myscheduler.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private PlanMapper planMapper;

    @Autowired
    ScheduleService scheduleService;

    @GetMapping("/plan")
    public List<Plan> getByMonth() {
        return planMapper.selectWithPeriodAndUserId("2022-04-01", "2022-05-01", 1);
    }

    @GetMapping("/dailyPlan/{date}")
    public List<Plan> getByDate(@PathVariable String date) {
        return planMapper.selectWithPlannedDateAndUserId(date, 1);
    }

    @GetMapping("/monthly/{yearMonth}")
    public Map<Integer, List<Plan>> getMonthly(@PathVariable String yearMonth) {
        return scheduleService.getMonthly(yearMonth);
    }

    @GetMapping("/daily/{yearMonth}/{day}")
    public List<Plan> getByDate(@PathVariable String yearMonth,
                                @PathVariable String day) {
        return scheduleService.getDaily(yearMonth, day);
    }

}
