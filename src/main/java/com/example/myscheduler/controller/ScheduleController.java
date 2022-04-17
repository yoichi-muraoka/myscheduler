package com.example.myscheduler.controller;

import com.example.myscheduler.service.ScheduleService;
import com.example.myscheduler.util.YearMonthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Year;

@Controller
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public String index(
            @RequestParam(name = "yearMonth", required = false) String yearMonth,
            Model model) {
        // Getパラメータとして年月を取得
        if(yearMonth == null) {
            yearMonth = YearMonthUtil.getCurrentMonth();
        }

        // 当月のスケジュール
        model.addAttribute("schedule", scheduleService.getMonthly(yearMonth));
        // 月初と月末の曜日情報(整数)
        model.addAttribute("daysOfWeeks", YearMonthUtil.getFirstAndLastDayOfWeek(yearMonth));
        // 前月の末日
        model.addAttribute("lastDateOfPrevMonth", YearMonthUtil.getLastDateOfPrevMonth(yearMonth));
        // 当月, 前月, 翌月(リンク等に使用)
        model.addAttribute("currentMonth", yearMonth);
        model.addAttribute("prevMonth", YearMonthUtil.getPrevMonth(yearMonth));
        model.addAttribute("nextMonth", YearMonthUtil.getNextMonth(yearMonth));
        return "index";
    }

    @GetMapping("/plans")
    public String plans() {
        return "plans";
    }

}
