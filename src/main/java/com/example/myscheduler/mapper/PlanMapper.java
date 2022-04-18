package com.example.myscheduler.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.myscheduler.domain.Plan;

@Mapper
public interface PlanMapper {

    List<Plan> selectWithPeriodAndUserId(@Param("startDate") String startDate,
                                         @Param("oneDayAfterEndDate") String oneDayAfterEndDate,
                                         @Param("userId") Integer userId);

    List<Plan> selectWithPlannedDateAndUserId(@Param("plannedDate") String plannedDate,
                                              @Param("userId") Integer userId);
    
    void insert(Plan plan);

    void deletePlan(@Param("planId") Integer planId,
                    @Param("userId") Integer userId);

}
