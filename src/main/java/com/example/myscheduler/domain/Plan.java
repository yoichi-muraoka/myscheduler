package com.example.myscheduler.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Plan {

    private Integer id;
    private Integer userId;
    private LocalDate plannedAt;
    private LocalTime startAt;
    private LocalTime endAt;
    
    @NotBlank
    @Size(max = 30)
    private String title;
    
    @Size(max = 255)
    private String description;

}
