package com.example.myscheduler.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.myscheduler.domain.Plan;
import com.example.myscheduler.service.ScheduleService;
import com.example.myscheduler.util.YearMonthUtil;

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

    @GetMapping("/daily")
    public String dailyPlans(
            @RequestParam String yearMonth,
            @RequestParam String date,
            Model model) {
    	// フォーム用
    	model.addAttribute("plan", new Plan());
        // 当日のスケジュール
        model.addAttribute("dailyPlans", scheduleService.getDaily(yearMonth, date));
        // 日付, 当月, 前月, 翌月(リンク等に使用)
        model.addAttribute("date", date);
        model.addAttribute("currentMonth", yearMonth);
        model.addAttribute("prevMonth", YearMonthUtil.getPrevMonth(yearMonth));
        model.addAttribute("nextMonth", YearMonthUtil.getNextMonth(yearMonth));
        return "daily";
    }
    
    @PostMapping("/daily")
    public String addPlan(
    		@RequestParam String yearMonth,
            @RequestParam String date,
            Plan plan,
            Model model) {
    	
    	// 予定の日付をセット
    	int[] ym = YearMonthUtil.getYearAndMonth(yearMonth);
    	LocalDate plannedAt = LocalDate.of(ym[0], ym[1], Integer.parseInt(date));
    	plan.setPlannedAt(plannedAt);
    	
    	// 予定を登録
    	scheduleService.addPlan(plan);
    	
    	// 元のページへリダイレクト
    	return "redirect:/daily?yearMonth=" + yearMonth + "&date=" + date;
    }

    @GetMapping("/daily/delete")
    public String deletePlan(
            @RequestParam String id,
            @RequestParam String yearMonth,
            @RequestParam String date) {
        scheduleService.deletePlan(id);
        return "redirect:/daily?yearMonth=" + yearMonth + "&date=" + date;
    }

}
