package com.example.myscheduler.mapper;

import com.example.myscheduler.domain.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlanMapper {

    List<Plan> selectWithPeriodAndUserId(@Param("startDate") String startDate,
                                         @Param("oneDayAfterEndDate") String oneDayAfterEndDate,
                                         @Param("userId") Integer userId);

    List<Plan> selectWithPlannedDateAndUserId(@Param("plannedDate") String plannedDate,
                                              @Param("userId") Integer userId);

}
