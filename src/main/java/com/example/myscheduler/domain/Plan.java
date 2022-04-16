package com.example.myscheduler.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Plan {

    private Integer id;
    private Integer userId;
    private LocalDate plannedAt;
    private LocalTime startAt;
    private LocalTime endAt;
    private String title;
    private String description;

}
