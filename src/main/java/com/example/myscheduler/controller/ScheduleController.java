package com.example.myscheduler.controller;

import com.example.myscheduler.service.ScheduleService;
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
        model.addAttribute("schedule", scheduleService.getMonthly(yearMonth));
        return "index";
    }

    @GetMapping("/plans")
    public String plans() {
        return "plans";
    }

}
