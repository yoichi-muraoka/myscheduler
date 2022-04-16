package com.example.myscheduler.test;

import com.example.myscheduler.domain.Plan;
import com.example.myscheduler.mapper.PlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private PlanMapper planMapper;

    @GetMapping("/plan")
    public List<Plan> getByMonth() {
        return planMapper.selectWithPeriodAndUserId("2022-04-01", "2022-05-01", 1);
    }

}
