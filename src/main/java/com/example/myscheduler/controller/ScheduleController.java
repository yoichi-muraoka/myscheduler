package com.example.myscheduler.controller;

import com.example.myscheduler.service.ScheduleService;
import com.example.myscheduler.util.YearMonthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public String index(Model model) {
        String yearMonth = "2022-04";
        // 当月のスケジュール
        model.addAttribute("schedule", scheduleService.getMonthly(yearMonth));
        // 月初と月末の曜日情報(整数)
        model.addAttribute("daysOfWeeks", YearMonthUtil.getFirstAndLastDayOfWeek(yearMonth));
        // 前月の末日
        model.addAttribute("lastDateOfPrevMonth", YearMonthUtil.getLastDateOfPrevMonth(yearMonth));
        return "index";
    }

    @GetMapping("/plans")
    public String plans() {
        return "plans";
    }

}
