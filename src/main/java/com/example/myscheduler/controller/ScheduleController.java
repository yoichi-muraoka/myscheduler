package com.example.myscheduler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/plans")
    public String plans() {
        return "plans";
    }

}
