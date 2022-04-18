package com.example.myscheduler.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.myscheduler.domain.User;

@Mapper
public interface UserMapper {

	User selectByLoginId(String loginId);

}
